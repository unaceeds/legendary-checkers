package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Class MulCirclesExpression.
 */
public class MulCirclesExpression extends AbstractCirclesExpression {

	/**
	 * Instantiates a new mul circles expression.
	 *
	 * @param visualBoard the visual board
	 */
	public MulCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#
	 * interpreteQueries(java.lang.String[])
	 */
	@Override
	public void interpreteQueries(String[] queries) {
		String abc = queries[1];
		for (int i = 2; i < queries.length; i++) {
			abc += " " + queries[i];
		}
		visualBoard.setMessage(abc);
		visualBoard.selected = -1;
	}

}
