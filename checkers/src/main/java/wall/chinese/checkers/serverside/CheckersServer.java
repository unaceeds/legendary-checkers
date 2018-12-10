package wall.chinese.checkers.serverside;

import java.io.IOException;
import java.net.ServerSocket;

import wall.chinese.checkers.clientside.board.CogTypes;

public class CheckersServer 
{
	private final int PORT = 9001;
	private final int maxCountOfPlayers = 2;
	private ServerSocket listener;
	private CogTypes[] startTwoPlayers = {CogTypes.EAX, CogTypes.EDX}, 
			startThreePlayers = {CogTypes.EAX, CogTypes.ECX, CogTypes.EEX}, 
			startFourPlayers = {CogTypes.EBX, CogTypes.ECX, CogTypes.EEX, CogTypes.EFX},
			startSixPlayers = {CogTypes.EAX,CogTypes.EBX, CogTypes.ECX, CogTypes.EDX, CogTypes.EEX, CogTypes.EFX};
			
	
	public CheckersServer() throws Exception
	{
		listener = new ServerSocket(PORT);
        System.out.println("Server is Running");
		try
		{
            Game game = new Game(maxCountOfPlayers);
            for(int i = 0; i < maxCountOfPlayers; i++)
            {
            	switch(maxCountOfPlayers)
            	{
            		case 2:
                    	game.addPlayer(game.new Player(listener.accept(), startTwoPlayers[i]));
                    	break;
            		case 3:
                    	game.addPlayer(game.new Player(listener.accept(), startThreePlayers[i]));
                    	break;
            		case 4:
                    	game.addPlayer(game.new Player(listener.accept(), startFourPlayers[i]));
                    	break;
            		case 6:
                    	game.addPlayer(game.new Player(listener.accept(), startSixPlayers[i]));
                    	break;
            	}
            }

            for(int i = 0; i < game.getPlayers().length; i++)
            	game.getPlayers()[i].start();
            
		}
		finally
		{
			listener.close();
		}
		
	}
	
	public static void main(String[] args) throws Exception
	{
		new CheckersServer();
	}
}
