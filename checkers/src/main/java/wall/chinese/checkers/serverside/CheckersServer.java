package wall.chinese.checkers.serverside;

import java.io.IOException;
import java.net.ServerSocket;

public class CheckersServer 
{
	private final int PORT = 9001;
	private final int maxCountOfPlayers = 2;
	private ServerSocket listener;
	
	public CheckersServer() throws Exception
	{
		listener = new ServerSocket(PORT);
		try
		{
            Game game = new Game(maxCountOfPlayers);
            for(int i = 0; i < maxCountOfPlayers; i++)
            	game.addPlayer(new Player(listener.accept()));
            
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
