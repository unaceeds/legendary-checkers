package wall.chinese.checkers.serverside;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread
{
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
}
