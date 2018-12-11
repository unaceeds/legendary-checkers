package wall.chinese.checkers.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.serverside.Game.Player;

public class Game 
{
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
	
	public Player theWinnerIs()
	{
		CogTypes playerCogType;
		int opponentSection;
		List<List<Field>> playerSections = insideBoard.getPlayerSections();
		for(Player player : players)
		{
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
					return player;
				}
			}
		}
		return null;
	}
	public void sendWholeBoard(PrintWriter output)
	{
		for(Field field: insideBoard.getFields())
		{
			if(field.getCogType() == CogTypes.EBP)
				output.println("SUB " + field.getCogType().toString() + " " + 
					insideBoard.getFields().indexOf(field));
			else
				output.println("ADD " + field.getCogType().toString() + " " + 
					insideBoard.getFields().indexOf(field));
		}
	}
	public void sendPossibleMoves(PrintWriter output, CogTypes cogType, int fieldIndex, boolean afterJump)
	{
		List<Integer> possibleMoves = insideBoard.getPossibleMoves(cogType, fieldIndex, afterJump);
		String command = "SUB " + cogType.toString() + " ";
		for(int i = 0; i < possibleMoves.size(); i++)
		{
			command += possibleMoves.get(i) + " ";
		}
		output.println(command);
	}
	

	
	class Player extends Thread
	{
		private Socket socket;
		private BufferedReader input;
		private PrintWriter output;
		private CogTypes myCogType; 
		private CogTypes opponentCogType;
		
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
		{	//TODO: all operation should be done only if player == currentPlayer!!!
			try
			{
				output.println("YOU " + myCogType.toString());
				sendWholeBoard(output);
				while(true)
				{
					String command = input.readLine();
					String[] commands = command.split(" ");
					if(commands[0].equals("BMOV"))
					{
						sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
								Integer.parseInt(commands[2]), false);
					}
					else if(commands[0].equals("MOV"))
					{
						//TODO who checks move, client or server?
						insideBoard.move(CogTypes.valueOf(commands[1]), Integer.parseInt(commands[2]), 
								Integer.parseInt(commands[3]));
						sendWholeBoard(output);
						if(theWinnerIs() != null)
						{
							//TODO how to inform about end of game?
						}
						//TODO change player when commands[1] == commands[2]
						if(wasJumped(Integer.parseInt(commands[2]), Integer.parseInt(commands[3])) == true)
						{
							sendPossibleMoves(output, CogTypes.valueOf(commands[1]), 
									Integer.parseInt(commands[3]), true);
						}
						
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
		
	}
}

