package wall.chinese.checkers.clientside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * The Class CheckersClient.
 */
public class CheckersClient {

	/** The Constant PORT. */
	private static final int PORT = 9001;
	
	/** The socket. */
	private Socket socket;
	
	/** The input. */
	private BufferedReader input;
	
	/** The output. */
	private PrintWriter output;
	
	/** The response. */
	private String response;

	/**
	 * Instantiates a new checkers client.
	 *
	 * @throws ConnectException the connect exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CheckersClient() throws IOException {

		socket = new Socket("localhost", PORT);
		input = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream(), true);

		CheckersClientGUI checkersClientGUI = new CheckersClientGUI(output);

		while (true) {
			response = input.readLine();
			//System.out.println(response);
			checkersClientGUI.getBoard().interprete(response);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		new CheckersClient();
	}

}
