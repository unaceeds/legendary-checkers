package wall.chinese.checkers.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Random;

import wall.chinese.checkers.clientside.board.CogTypes;

/**
 * Represents single game, {@link Player} is inner class.
 * @author piotr
 */
public class Game 
{	//TODO what should be synchronized?
	//TODO player can give up
	//TODO PLAYER CAN'T MOVE OUT OF OPPONENT SECTION!
	
	private InsideBoard insideBoard;
	/**
	 * {@link CheckersServer#maxCountOfPlayers}
	 */
	private int maxCountOfPlayers;
	private int countOfConnectedPlayers = 0;
	private Player[] players;
	/**
	 * Player whose round is now and who only can do something on board at the moment.
	 */
	private Player currentPlayer;
	
	/**
	 * startPlayers contains cogTypes of players in given game,
	 * it is one of the following arrays : startTwoPlayers, startThreePlayers,
	 * startFourPlayers, startSixPlayers, depending on how many players take part in a game
	 */
	private CogTypes[] startPlayers, startTwoPlayers = {CogTypes.EAX, CogTypes.EDX}, 
			startThreePlayers = {CogTypes.EAX, CogTypes.ECX, CogTypes.EEX}, 
			startFourPlayers = {CogTypes.EBX, CogTypes.ECX, CogTypes.EEX, CogTypes.EFX},
			startSixPlayers = {CogTypes.EAX,CogTypes.EBX, CogTypes.ECX, CogTypes.EDX, CogTypes.EEX, CogTypes.EFX};
	
	
	public Game(int maxCountOfPlayers)
	{
		this.maxCountOfPlayers = maxCountOfPlayers;
		players = new Player[maxCountOfPlayers];
		insideBoard = new InsideBoard();
    	switch(maxCountOfPlayers)
    	{
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
	
	public void addPlayer(Player player)
	{
		if(countOfConnectedPlayers < maxCountOfPlayers)
		{
			players[countOfConnectedPlayers] = player;
			countOfConnectedPlayers++;
		}
	}
	
	public Player[] getPlayers()
	{
		return players;
	}
	/**
	 * set player who should start a game
	 */
	public void setRandomCurrentPlayer()
	{
		int rnd = new Random().nextInt(players.length);
		currentPlayer = players[rnd];
	}
	/**
	 * @param player player for whom we wants to check if he's got all pawns in opponent section
	 * @return true if he should end a game, false if he should continue playing
	 */
	private boolean isWinner(Player player)
	{
		CogTypes playerCogType;
		int opponentSection;
		List<List<Field>> playerSections = insideBoard.getPlayerSections();
		playerCogType = player.myCogType;
		opponentSection = player.opponentCogType.ordinal();
		for(Field field : playerSections.get(opponentSection))
		{
			if(field.getCogType() != playerCogType)
			{
				break;
			}
			// if it is the end of array, we have the winner
			if(field == playerSections.get(opponentSection).get(playerSections.get(opponentSection).size() - 1))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sends whole board to all players.
	 */
	private void sendWholeBoardToPlayers()
	{
		for(Field field: insideBoard.getFields())
		{
			if(field.getCogType() == CogTypes.EBP) // black fields shouldn't be filled, so we use SUB
				for(int i = 0; i < players.length; i++)
					players[i].output.println("SUB " + field.getCogType().toString() + " " + 
						insideBoard.getFields().indexOf(field));
			else
				for(int i = 0; i < players.length; i++)
					players[i].output.println("ADD " + field.getCogType().toString() + " " + 
						insideBoard.getFields().indexOf(field));
		}
	}
	/**
	 * @param output output which function should send response to
	 * @param cogType {@link InsideBoard#getPossibleMoves}
	 * @param fieldIndex {@link InsideBoard#getPossibleMoves}
	 * @param afterJump {@link InsideBoard#getPossibleMoves}
	 */
	private void sendPossibleMoves(PrintWriter output, CogTypes cogType, int fieldIndex, boolean afterJump)
	{
		List<Integer> possibleMoves = insideBoard.getPossibleMoves(cogType, fieldIndex, afterJump);
		String command = "SUB " + cogType.toString() + " ";
		for(int i = 0; i < possibleMoves.size(); i++)
		{
			command += possibleMoves.get(i) + " ";
		}
		output.println(command);
	}

	/**
	 * Allows to change current player to the next one(clockwise)
	 */
	private void changePlayer()
	{
		int i = 0;
		while(players[i] != currentPlayer) // searching for next player 
			i++;
		if(i == players.length - 1) // if last player in array was able to move, we'd go back to the beginning of array
			i = 0;
		else
			i++;
		if(players[i].finishedGame) // if somebody finished game, he can't play
			changePlayer();
		currentPlayer = players[i];
		System.out.println(currentPlayer.myCogType.toString());
	}
	private boolean isEveryoneFinished()
	{
		int winners = 0;
		for(int j = 0; j < players.length; j++)
			if(players[j].finishedGame)
				winners++;
		if(winners == players.length)
		{
			return true;
		}
		return false;
	}

	
	/**
	 * @author piotr
	 * Represents particular player in a game, using threads.
	 */
	class Player extends Thread
	{
		private Socket socket;
		private BufferedReader input;
		private PrintWriter output;
		private CogTypes myCogType; 
		private CogTypes opponentCogType;
		private boolean finishedGame = false;
		
		public Player(Socket socket, int indexOfPlayer)
		{
			this.socket = socket;
			myCogType = startPlayers[indexOfPlayer];
			setOpponentCogType();
            try 
            {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } 
            catch (IOException e) 
            {
                System.out.println("Player died: " + e);
            }
		}

		@Override
		public void run()
		{	
			try
			{
				output.println("YOU " + myCogType.toString()); // tell client which cogType is his
				sendWholeBoardToPlayers();
				while(true) //infinity loop that reads input from client and react
				{
					String command = input.readLine();
					//System.out.println(command);
					String[] commands = command.split(" ");
					if(commands[0].equals("BMOV") && isGoodPlayer()) //before mov - sending possible moves
					{
						sendWholeBoardToPlayers();
						sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
								Integer.parseInt(commands[2]), false);
					}
					else if(commands[0].equals("MOV")) // move
					{
						if(isGoodPlayer())
							insideBoard.move(CogTypes.valueOf(commands[1]), Integer.parseInt(commands[2]), 
								Integer.parseInt(commands[3]));
						sendWholeBoardToPlayers();
						if(isWinner(this) && isGoodPlayer())
						{
							//TODO how to inform about end of game?
							finishedGame = true;
							if(isEveryoneFinished())
							{
								//TODO what to do when everyone finished?
							}
						}
						if(Integer.parseInt(commands[2]) == Integer.parseInt(commands[3])) // MOV 2 2 - end of jumping
						{
							sendWholeBoardToPlayers();
							if(isGoodPlayer())
								changePlayer();
						}
						if(wasJumped(Integer.parseInt(commands[2]), Integer.parseInt(commands[3])) && 
								isGoodPlayer())
						{	// after jump we immediately want to show possibilites 
							sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
									Integer.parseInt(commands[3]), true);
						}
						else if(isGoodPlayer()) // after normal move
							changePlayer();
						
					}
				}
			}
			catch(IOException e)
			{
                System.out.println("Player died: " + e);
			}
			finally 
			{
                try 
                {
                	socket.close();
                } 
                catch (IOException e) {}
            }
		}
		
		/**
		 * @param oldFieldIndex index of field that player starts from
		 * @param newFieldIndex index of field that player ends on
		 * @return true if move was 'jump' over another pawn, false if move was 'normal' 
		 */
		private boolean wasJumped(int oldFieldIndex, int newFieldIndex)
		{
			if(oldFieldIndex == newFieldIndex)
				return false;
			List<Field> fields = insideBoard.getFields();
			Field oldField = fields.get(oldFieldIndex);
			for(int i = 0; i < oldField.getNeighbours().length; i++)
			{	//check if move was only to close neighbour or somewhere further
				if(oldField.getNeighbours()[i] == newFieldIndex)
				{
					return false;
				}
			}
			return true;
		}
		/**
		 * Sets who is our opponent, it is symetric
		 */
		private void setOpponentCogType()
		{
			if(myCogType == CogTypes.EAX)
				opponentCogType = CogTypes.EDX;
			else if (myCogType == CogTypes.EBX)
				opponentCogType = CogTypes.EEX;
			else if (myCogType == CogTypes.ECX)
				opponentCogType = CogTypes.EFX;
			else if (myCogType == CogTypes.EDX)
				opponentCogType = CogTypes.EAX;
			else if (myCogType == CogTypes.EEX)
				opponentCogType = CogTypes.EBX;
			else if (myCogType == CogTypes.EFX)
				opponentCogType = CogTypes.ECX;
		}
		/**
		 * @return true if this player has now his round, false otherwise
		 */
		private boolean isGoodPlayer()
		{
			int i = 0;
			while(startPlayers[i] != myCogType)
				i++;
			
			if(players[i] == currentPlayer)
				return true;
			else
				return false;
		}
	}
}

