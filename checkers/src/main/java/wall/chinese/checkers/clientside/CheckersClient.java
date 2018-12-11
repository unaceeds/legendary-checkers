package wall.chinese.checkers.clientside;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CheckersClient {

	private static final int PORT = 9001;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private String response;

	public CheckersClient() throws Exception {

		socket = new Socket("localhost", PORT);
		input = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream(), true);

		CheckersClientGUI checkersClientGUI = new CheckersClientGUI(output);

		while (true) {
			response = input.readLine();
			System.out.println(response);
			checkersClientGUI.getBoard().interprete(response);
		}
	}

	public static void main(String[] args) throws Exception {
		new CheckersClient();
	}

}
