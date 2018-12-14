package wall.chinese.checkers.serverside;

import java.io.IOException;
import java.net.ServerSocket;

import wall.chinese.checkers.clientside.board.CogTypes;

/**
 * CheckersServer runs server for ChineseCheckers game, waits for players and add them to the game.
 * @author piotr
 */

public class CheckersServer 
{
	private final int PORT = 9001;
	/**
	 * This variable can be only 2, 3, 4 or 6. Server runs game only for these number of players.
	 */
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
            	game.addPlayer(game.new Player(listener.accept(), i)); // sending socket and index of players
            
            game.setRandomCurrentPlayer(); // who starts
            
            for(int i = 0; i < game.getPlayers().length; i++)
            	game.getPlayers()[i].start(); // now all threads call run() function
            
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
