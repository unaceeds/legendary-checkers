package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Class AbstractCirclesExpression.
 */
public abstract class AbstractCirclesExpression {

	/** The visual board. */
	protected VisualBoard visualBoard;

	/**
	 * Instantiates a new abstract circles expression.
	 *
	 * @param visualBoard the visual board
	 */
	protected AbstractCirclesExpression(VisualBoard visualBoard) {
		this.visualBoard = visualBoard;
	}

	/**
	 * Interprete queries.
	 *
	 * @param queries the queries
	 */
	public abstract void interpreteQueries(String[] queries);

}