package wall.chinese.checkers.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wall.chinese.checkers.clientside.board.CogTypes;

/**
 * Represents single game, {@link Player} is inner class.
 * 
 * @author piotr
 */
public class Game { // TODO what should be synchronized?

	private InsideBoard insideBoard;
	/**
	 * {@link CheckersServer#maxCountOfPlayers}
	 */
	private int maxCountOfPlayers;
	private int countOfConnectedPlayers = 0;
	/**
	 * False if at least one player is still playing, true if everyone finished.
	 */
	private boolean allFinished = false;
	private Player[] players;
	/**
	 * Player whose round is now and who only can do something on board at the
	 * moment.
	 */
	private Player currentPlayer;

	/**
	 * startPlayers contains cogTypes of players in given game, it is one of the
	 * following arrays : startTwoPlayers, startThreePlayers, startFourPlayers,
	 * startSixPlayers, depending on how many players take part in a game
	 */
	private CogTypes[] startPlayers,
			startTwoPlayers = { CogTypes.EAX, CogTypes.EDX },
			startThreePlayers = { CogTypes.EAX, CogTypes.ECX, CogTypes.EEX },
			startFourPlayers = { CogTypes.EBX, CogTypes.ECX, CogTypes.EEX,
					CogTypes.EFX },
			startSixPlayers = { CogTypes.EAX, CogTypes.EBX, CogTypes.ECX,
					CogTypes.EDX, CogTypes.EEX, CogTypes.EFX };

	public Game(int maxCountOfPlayers) {
		this.maxCountOfPlayers = maxCountOfPlayers;
		players = new Player[maxCountOfPlayers];
		insideBoard = new InsideBoard();
		switch (maxCountOfPlayers) {
		case 2:
			startPlayers = startTwoPlayers;
			break;
		case 3:
			startPlayers = startThreePlayers;
			break;
		case 4:
			startPlayers = startFourPlayers;
			break;
		case 6:
			startPlayers = startSixPlayers;
			break;
		}

		insideBoard.fillStartedBoard(startPlayers);

	}

	public void addPlayer(Player player) {
		if (countOfConnectedPlayers < maxCountOfPlayers) {
			players[countOfConnectedPlayers] = player;
			countOfConnectedPlayers++;
			player.number = countOfConnectedPlayers;
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	/**
	 * set player who should start a game
	 */
	public void setRandomCurrentPlayer() {
		int rnd = new Random().nextInt(players.length);
		currentPlayer = players[rnd];
	}

	/**
	 * @param player player for whom we wants to check if he's got all pawns in
	 *               opponent section
	 * @return true if he should end a game, false if he should continue playing
	 */
	private boolean isWinner(Player player) {
		CogTypes playerCogType;
		int oppositeSection;
		List<List<Field>> playerSections = insideBoard.getPlayerSections();
		playerCogType = player.myCogType;
		oppositeSection = player.oppositeCogType.ordinal();
		for (Field field : playerSections.get(oppositeSection)) {
			if (field.getCogType() != playerCogType) {
				break;
			}
			// if it is the end of array, we have the winner
			if (field == playerSections.get(oppositeSection)
					.get(playerSections.get(oppositeSection).size() - 1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sends whole board to all players.
	 */
	private void sendWholeBoardToPlayers() {
		for (Field field : insideBoard.getFields()) {
			if (field.getCogType() == CogTypes.EBP) {// black fields shouldn't
														// be filled, so we use
														// SUB
				for (int i = 0; i < players.length; i++) {
					if (!players[i].bot)
						players[i].output.println("SUB "
								+ field.getCogType().toString() + " "
								+ insideBoard.getFields().indexOf(field));
				}
			} else {
				for (int i = 0; i < players.length; i++) {
					if (!players[i].bot)
						players[i].output.println("ADD "
								+ field.getCogType().toString() + " "
								+ insideBoard.getFields().indexOf(field));
				}
			}
		}
	}

	/**
	 * @param output     output which function should send response to
	 * @param cogType    {@link InsideBoard#getPossibleMoves}
	 * @param oppCogType {@link InsideBoard#getPossibleMoves}
	 * @param fieldIndex {@link InsideBoard#getPossibleMoves}
	 * @param afterJump  {@link InsideBoard#getPossibleMoves}
	 */
	private void sendPossibleMoves(PrintWriter output, CogTypes cogType,
			CogTypes oppCogType, int fieldIndex, boolean afterJump) {
		List<Integer> possibleMoves = insideBoard.getPossibleMoves(cogType,
				oppCogType, fieldIndex, afterJump);
		String command = "SUB " + cogType.toString() + " ";
		for (int i = 0; i < possibleMoves.size(); i++) {
			command += possibleMoves.get(i) + " ";
		}
		output.println(command);
	}

	/**
	 * Allows to change current player to the next one(clockwise)
	 */
	private void changePlayer() {
		int i = 0;
		while (players[i] != currentPlayer) // searching for next player
			i++;
		if (i == players.length - 1) // if last player in array was able to
										// move, we'd go back to the beginning
										// of array
			i = 0;
		else
			i++;
		currentPlayer = players[i];
		if (players[i].finished) // if somebody finished game, he can't play
			changePlayer();
		sendMessageToEveryone(
				"Now player number " + currentPlayer.number + " is playing");
	}

	private void sendMessageToEveryone(String message) {
		for (Player player : players)
			if (!player.bot)
				player.output.println("MUL " + message);
	}

	private boolean isEveryoneFinished() {
		int winners = 0;
		for (int j = 0; j < players.length; j++)
			if (players[j].finished)
				winners++;
		if (winners == players.length) {
			return true;
		}
		return false;
	}

	/**
	 * @author piotr Represents particular player in a game, using threads.
	 */
	class Player extends Thread {
		private int number;
		private Socket socket;
		private BufferedReader input;
		private PrintWriter output;
		private CogTypes myCogType;
		private CogTypes oppositeCogType;
		private boolean finished = false;
		private boolean bot;

		public Player(Socket socket, int indexOfPlayer, boolean bot) {
			this.bot = bot;
			myCogType = startPlayers[indexOfPlayer];
			setOppositeCogType();
			if (!bot) {
				this.socket = socket;
				try {
					input = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(socket.getOutputStream(), true);
				} catch (IOException e) {
					System.out.println("Player died: " + e);
				}
			}
		}

		@Override
		public void run() {
			if (bot) {
				while (true) {
					if (isGoodPlayer() && !finished && !allFinished) {
						List<Integer> listA = new ArrayList<Integer>();
						List<List<Integer>> listB = new ArrayList<List<Integer>>();
						for (Field field : insideBoard.getFields()) {
							if (field.getCogType() == myCogType) {
								listA.add(
										insideBoard.getFields().indexOf(field));
								listB.add(insideBoard.getPossibleMoves(
										myCogType, oppositeCogType,
										listA.get(listA.size() - 1), false));
								if (listB.get(listA.size() - 1).size() == 0) {
									listA.remove(listA.size() - 1);
									listB.remove(listB.size() - 1);
								}
							}
						}
						int a = (int) (Math.random() * listA.size());
						int oldFieldIndex = listA.get(a);

						int min = 17;
						int b = (int) (Math.random() * listB.get(a).size());
						int newFieldIndex = listB.get(a).get(b);

						for (Integer integer : listB.get(a)) {
							if (insideBoard.distancesForField[oppositeCogType
									.ordinal()][integer] <= min) {
								min = insideBoard.distancesForField[oppositeCogType
										.ordinal()][integer];
								newFieldIndex = integer;
							}
						}
						insideBoard.move(myCogType, oldFieldIndex,
								newFieldIndex);
						try {
							sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						/*
						 * int n = 0; while (n < 4 &&
						 * insideBoard.wasJumped(oldFieldIndex, newFieldIndex))
						 * { oldFieldIndex = newFieldIndex; List<Integer> listC
						 * = new ArrayList<Integer>(); listC =
						 * insideBoard.getPossibleMoves(myCogType,
						 * oppositeCogType, oldFieldIndex, true); if
						 * (listC.size() == 0) break; int c = (int)
						 * Math.random() * listC.size(); newFieldIndex =
						 * listC.get(c); insideBoard.move(myCogType,
						 * oldFieldIndex, newFieldIndex); n++; }
						 */
						if (isWinner(this)) {
							finished = true;
							sendMessageToEveryone("Player number " + number
									+ " successfully finished his game");
							if (isEveryoneFinished()) {
								allFinished = true;
								sendMessageToEveryone(
										"End of the game, everyone successfully finished");
							}
						}
						if (!allFinished)
							changePlayer();
						sendWholeBoardToPlayers();
					}
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// return;
			String command;
			String[] commands;
			String[] prevCommands = null; // previous commands array
			try {
				output.println("YOU " + myCogType.toString()); // tell client
																// which cogType
																// is his
				sendMessageToEveryone("Now player number "
						+ currentPlayer.number + " is playing");
				sendWholeBoardToPlayers();
				while (true) // infinity loop that reads input from client and
								// react
				{
					command = input.readLine();
					// System.out.println(command);
					commands = command.split(" ");
					if (commands[0].equals("BMOV") && isGoodPlayer()
							&& !allFinished) // before mov - sending possible
												// moves
					{
						sendWholeBoardToPlayers();
						// solution of following cheat: jump, an then request
						// BMOV for another pawn
						if (prevCommands == null || prevCommands.length != 4
								|| !insideBoard.wasJumped(
										Integer.parseInt(prevCommands[2]),
										Integer.parseInt(prevCommands[3]))) {
							sendPossibleMoves(output,
									CogTypes.valueOf(commands[1]),
									oppositeCogType,
									Integer.parseInt(commands[2]), false);
						} else if (prevCommands != null
								&& prevCommands.length == 4
								&& insideBoard.wasJumped(
										Integer.parseInt(prevCommands[2]),
										Integer.parseInt(prevCommands[3]))) {
							prevCommands = null;
							changePlayer();
						}
					} else if (commands[0].equals("MOV") && !allFinished) // move
					{
						if (isGoodPlayer())
							insideBoard.move(CogTypes.valueOf(commands[1]),
									Integer.parseInt(commands[2]),
									Integer.parseInt(commands[3]));

						sendWholeBoardToPlayers();

						if (isWinner(this) && isGoodPlayer()) {
							finished = true;
							if (isEveryoneFinished())
								allFinished = true;
						}

						if (Integer.parseInt(commands[2]) == Integer
								.parseInt(commands[3])) // MOV 2 2 - end of
														// jumping
						{
							sendWholeBoardToPlayers();
							if (isGoodPlayer() && !allFinished)
								changePlayer();
						}

						if (insideBoard.wasJumped(Integer.parseInt(commands[2]),
								Integer.parseInt(commands[3])) && isGoodPlayer()
								&& !allFinished) { // after jump we immediately
													// want to show possibilites
							sendPossibleMoves(output,
									CogTypes.valueOf(commands[1]),
									oppositeCogType,
									Integer.parseInt(commands[3]), true);
						} else if (isGoodPlayer() && !allFinished) // after
																	// normal
																	// move
							changePlayer();

						if (isWinner(this))
							sendMessageToEveryone("Player number " + number
									+ " successfully finished his game");

						if (isEveryoneFinished())
							sendMessageToEveryone(
									"End of the game, everyone successfully finished");

						prevCommands = commands.clone();
					}
				}
			} catch (IOException e) {
				System.out.println("Player died: " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}

		/**
		 * Sets who is our opponent, it is symetric
		 */
		private void setOppositeCogType() {
			if (myCogType == CogTypes.EAX)
				oppositeCogType = CogTypes.EDX;
			else if (myCogType == CogTypes.EBX)
				oppositeCogType = CogTypes.EEX;
			else if (myCogType == CogTypes.ECX)
				oppositeCogType = CogTypes.EFX;
			else if (myCogType == CogTypes.EDX)
				oppositeCogType = CogTypes.EAX;
			else if (myCogType == CogTypes.EEX)
				oppositeCogType = CogTypes.EBX;
			else if (myCogType == CogTypes.EFX)
				oppositeCogType = CogTypes.ECX;
		}

		/**
		 * @return true if this player has now his round, false otherwise
		 */
		private boolean isGoodPlayer() {
			int i = 0;
			while (startPlayers[i] != myCogType)
				i++;

			if (players[i] == currentPlayer)
				return true;
			else
				return false;
		}
	}
}
