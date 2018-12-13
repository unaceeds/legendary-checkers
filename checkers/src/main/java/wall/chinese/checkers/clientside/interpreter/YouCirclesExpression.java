package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Class YouCirclesExpression.
 */
public class YouCirclesExpression extends AbstractCirclesExpression {

	/**
	 * Instantiates a new you circles expression.
	 *
	 * @param visualBoard the visual board
	 */
	public YouCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	/* (non-Javadoc)
	 * @see wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])
	 */
	@Override
	public void interpreteQueries(String[] queries) {
		visualBoard.setMyCogType(CogTypes.valueOf(queries[1]));
	}

}
