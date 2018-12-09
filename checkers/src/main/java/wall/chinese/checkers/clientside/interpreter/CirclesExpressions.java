package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

public enum CirclesExpressions {
	MOV {
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new MovCirclesExpression(visualBoard);
		}
	},
	ADD {
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new AddCirclesExpression(visualBoard);
		}
	},
	SUB {
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new SubCirclesExpression(visualBoard);
		}
	};

	public AbstractCirclesExpression getCirclesExpression(
			VisualBoard visualBoard) {
		return null;
	}
}
