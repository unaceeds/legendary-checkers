package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.VisualBoard;

public enum CirclesExpressions {
	MOV {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new MovCirclesExpression(visualBoard);
		}
	},
	ADD {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new AddCirclesExpression(visualBoard);
		}
	},
	SUB {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new SubCirclesExpression(visualBoard);
		}
	},
	YOU {
		@Override
		public AbstractCirclesExpression getCirclesExpression(
				VisualBoard visualBoard) {
			return new YouCirclesExpression(visualBoard);
		}
	};

	public AbstractCirclesExpression getCirclesExpression(
			VisualBoard visualBoard) {
		return null;
	}
}
