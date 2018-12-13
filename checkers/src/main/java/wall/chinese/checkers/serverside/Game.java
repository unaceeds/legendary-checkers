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
import wall.chinese.checkers.serverside.Game.Player;

public class Game 
{	//TODO what should be synchronized?
	//TODO player can give up
	private InsideBoard insideBoard;
	private int maxCountOfPlayers;
	private int countOfConnectedPlayers = 0;
	private Player[] players;
	private Player currentPlayer;
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
	public void setRandomCurrentPlayer()
	{
		int rnd = new Random().nextInt(players.length);
		currentPlayer = players[rnd];
	}
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
	private void sendWholeBoardToPlayers()
	{
		for(Field field: insideBoard.getFields())
		{
			if(field.getCogType() == CogTypes.EBP)
				for(int i = 0; i < players.length; i++)
					players[i].output.println("SUB " + field.getCogType().toString() + " " + 
						insideBoard.getFields().indexOf(field));
			else
				for(int i = 0; i < players.length; i++)
					players[i].output.println("ADD " + field.getCogType().toString() + " " + 
						insideBoard.getFields().indexOf(field));
		}
	}
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

	private void changePlayer()
	{
		int i = 0;
		while(players[i] != currentPlayer)
			i++;
		if(i == players.length - 1)
			i = 0;
		else
			i++;
		if(players[i].finishedGame)
			changePlayer();
		currentPlayer = players[i];
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

		public void run()
		{	
			try
			{
				output.println("YOU " + myCogType.toString());
				sendWholeBoardToPlayers();
				while(true)
				{
					String command = input.readLine();
					//System.out.println(command);
					String[] commands = command.split(" ");
					if(commands[0].equals("BMOV") && isGoodPlayer())
					{
						sendWholeBoardToPlayers();
						sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
								Integer.parseInt(commands[2]), false);
					}
					else if(commands[0].equals("MOV"))
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
						if(Integer.parseInt(commands[2]) == Integer.parseInt(commands[3]))
						{
							sendWholeBoardToPlayers();
							if(isGoodPlayer())
								changePlayer();
						}
						if(wasJumped(Integer.parseInt(commands[2]), Integer.parseInt(commands[3])) && 
								isGoodPlayer())
						{
							sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
									Integer.parseInt(commands[3]), true);
						}
						else if(isGoodPlayer())
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
		
		private boolean wasJumped(int oldFieldIndex, int newFieldIndex)
		{
			if(oldFieldIndex == newFieldIndex)
				return false;
			List<Field> fields = insideBoard.getFields();
			Field oldField = fields.get(oldFieldIndex);
			for(int i = 0; i < oldField.getNeighbours().length; i++)
			{
				if(oldField.getNeighbours()[i] == newFieldIndex)
				{
					return false;
				}
			}
			return true;
		}
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

