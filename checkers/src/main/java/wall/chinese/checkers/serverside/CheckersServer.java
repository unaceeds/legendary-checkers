package wall.chinese.checkers.serverside;

import java.net.ServerSocket;

/**
 * CheckersServer runs server for ChineseCheckers game, waits for players and
 * add them to the game.
 * 
 * @author piotr
 */

public class CheckersServer {
	private final int PORT = 9001;
	/**
	 * This variable can be only 2, 3, 4 or 6. Server runs game only for these
	 * number of players.
	 */
	private final int maxCountOfPlayers = 3;
	private ServerSocket listener;

	public CheckersServer() throws Exception {
		listener = new ServerSocket(PORT);
		System.out.println("Server is Running");
		try {
			Game game = new Game(maxCountOfPlayers);
			game.addPlayer(game.new Player(listener.accept(), 0, false));
			for (int i = 1; i < maxCountOfPlayers; i++)
				game.addPlayer(game.new Player(null, i, true)); // sending
																// socket
																// and
																// index
																// of
																// players

			game.setRandomCurrentPlayer(); // who starts

			for (int i = 0; i < game.getPlayers().length; i++)
				game.getPlayers()[i].start(); // now all threads call run()
												// function

		} finally {
			listener.close();
		}

	}

	public static void main(String[] args) throws Exception {
		new CheckersServer();
	}
}
