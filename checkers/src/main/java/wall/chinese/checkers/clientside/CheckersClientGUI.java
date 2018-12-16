package wall.chinese.checkers.clientside;

import java.io.PrintWriter;

import javax.swing.JFrame;

import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Class CheckersClientGUI.
 */
@SuppressWarnings("serial")
public class CheckersClientGUI extends JFrame {

	/** The visual board. */
	private VisualBoard visualBoard;

	/**
	 * Instantiates a new checkers client GUI.
	 *
	 * @param output the output
	 */
	public CheckersClientGUI(PrintWriter output) {
		super("Client demo");
		visualBoard = new VisualBoard(output);
		add(visualBoard);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public VisualBoard getBoard() {
		return visualBoard;
	}

}