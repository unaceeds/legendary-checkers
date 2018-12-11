package wall.chinese.checkers.clientside;

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

	}
	
	public VisualBoard getBoard()
	{
		return visualBoard;
	}

}