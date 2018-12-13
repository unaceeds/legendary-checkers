package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * The Enum CirclesExpressions.
 */
public enum CirclesExpressions {

	/** The mov. */
	MOV {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new MovCirclesExpression(visualBoard);
		}
	},

	/** The add. */
	ADD {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new AddCirclesExpression(visualBoard);
		}
	},

	/** The sub. */
	SUB {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new SubCirclesExpression(visualBoard);
		}
	},

	/** The you. */
	YOU {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new YouCirclesExpression(visualBoard);
		}
	},

	/** The mul. */
	MUL {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new MulCirclesExpression(visualBoard);
		}
	};

	/**
	 * Gets the circles expression.
	 *
	 * @param visualBoard the visual board
	 * @return the circles expression
	 */
	public AbstractCirclesExpression getCirclesExpression(
			VisualBoard visualBoard) {
		return null;
	}
}
