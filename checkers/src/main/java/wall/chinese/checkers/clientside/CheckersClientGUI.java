package wall.chinese.checkers.clientside;

import java.io.PrintWriter;

import javax.swing.JFrame;

import wall.chinese.checkers.clientside.board.VisualBoard;

@SuppressWarnings("serial")
public class CheckersClientGUI extends JFrame {

	private VisualBoard visualBoard;

	public CheckersClientGUI(PrintWriter output) {
		super("Client demo");
		visualBoard = new VisualBoard(output);
		add(visualBoard);
		pack();
		setResizable(false);
		setVisible(true);

	}

	public VisualBoard getBoard() {
		return visualBoard;
	}

}