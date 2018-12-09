package wall.chinese.checkers.clientside;

import java.util.Scanner;

import javax.swing.JFrame;

import wall.chinese.checkers.clientside.board.VisualBoard;

@SuppressWarnings("serial")
public class CheckersClientGUI extends JFrame {

	private VisualBoard visualBoard;

	public CheckersClientGUI() {
		super("Client demo");
		visualBoard = new VisualBoard();
		add(visualBoard);
		pack();
		setResizable(false);
		setVisible(true);

		visualBoard.interprete("ADD EAX 0");
		visualBoard.interprete("ADD EBX 1");
		visualBoard.interprete("ADD ECX 2");
		visualBoard.interprete("ADD EDX 3");
		visualBoard.interprete("ADD EEX 4");
		visualBoard.interprete("ADD EFX 5");
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		while (line != "EXIT") {
			visualBoard.interprete(line);
			line = scanner.nextLine();
		}
		scanner.close();
	}

}