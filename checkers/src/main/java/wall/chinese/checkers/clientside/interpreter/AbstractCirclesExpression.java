package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

public abstract class AbstractCirclesExpression {

	protected VisualBoard visualBoard;

	protected AbstractCirclesExpression(VisualBoard visualBoard) {
		this.visualBoard = visualBoard;
	}

	public abstract void interpreteQueries(String[] queries);

}