package wall.chinese.checkers.serverside;

import java.io.IOException;
import java.net.ServerSocket;

import wall.chinese.checkers.clientside.board.CogTypes;

public class CheckersServer 
{
	private final int PORT = 9001;
	private final int maxCountOfPlayers = 2;
	private ServerSocket listener;
	
	public CheckersServer() throws Exception
	{
		listener = new ServerSocket(PORT);
        System.out.println("Server is Running");
		try
		{
            Game game = new Game(maxCountOfPlayers);
            for(int i = 0; i < maxCountOfPlayers; i++)
            	game.addPlayer(game.new Player(listener.accept(), i));
            
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
