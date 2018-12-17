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
	/**
	 * This variable tells how many of players 
	 * is going to be real players, not bots.
	 * It has to be not greater than {@link maxCountOfPlayers}.
	 */
	private final int countOfRealPlayers = 2;
	private ServerSocket listener;

	public CheckersServer() throws Exception {
		listener = new ServerSocket(PORT);
		System.out.println("Server is Running");
		try {
			Game game = new Game(maxCountOfPlayers);
			preparePlayersAndBots(game);

			game.setRandomCurrentPlayer(); // who starts

			for (int i = 0; i < game.getPlayers().length; i++)
				game.getPlayers()[i].start(); // now all threads call run()
												// function

		} finally {
			listener.close();
		}

	}
	/**
	 * This function instantiate new real players or bots 
	 * and adds them to the game.
	 * @param game
	 * @throws Exception
	 */
	private void preparePlayersAndBots(Game game) throws Exception
	{
		for(int i = 0; i < countOfRealPlayers; i++)
			game.addPlayer(game.new Player(listener.accept(), i, false));
		for(int i = countOfRealPlayers; i < maxCountOfPlayers; i++)
			game.addPlayer(game.new Player(null, i, true));
	}

	public static void main(String[] args) throws Exception {
		new CheckersServer();
	}
}
