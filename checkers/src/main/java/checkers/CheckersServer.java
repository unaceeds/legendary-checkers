package checkers;

import java.net.ServerSocket;

public class CheckersServer 
{
	private final int PORT = 8901;
	private ServerSocket listener;
	private Game currentGame;
}
