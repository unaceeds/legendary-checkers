package wall.chinese.checkers.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
		
		return null;
	}
	public void sendWholeBoard(PrintWriter output)
	{
		for(Field field: insideBoard.getFields())
		{
			System.out.println("ADD " + field.getCogType().toString() + " " + insideBoard.getFields().indexOf(field));
			output.println("ADD " + field.getCogType().toString() + " " + insideBoard.getFields().indexOf(field));
		}
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
		{
			try
			{
				sendWholeBoard(output);
				while(true)
				{
					String command = input.readLine();
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

