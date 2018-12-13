package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Class SubCirclesExpression.
 */
public class SubCirclesExpression extends AbstractCirclesExpression {

	/**
	 * Instantiates a new sub circles expression.
	 *
	 * @param visualBoard the visual board
	 */
	public SubCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	/* (non-Javadoc)
	 * @see wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])
	 */
	@Override
	public void interpreteQueries(String[] queries) {
		for (int i = 2; i < queries.length; i++) {
			visualBoard.mark(Integer.parseInt(queries[i]),
					CogTypes.valueOf(queries[1]), false);
		}
	}

}
