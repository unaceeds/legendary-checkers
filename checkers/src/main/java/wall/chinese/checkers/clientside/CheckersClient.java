package wall.chinese.checkers.clientside;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CheckersClient {

	private static final int PORT = 9001;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public CheckersClient() throws Exception
    {
		socket = new Socket("localhost", PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    
		CheckersClientGUI checkersClientGUI = new CheckersClientGUI();
    }

	public static void main(String[] args) throws Exception 
	{
		new CheckersClient();
	}

}
