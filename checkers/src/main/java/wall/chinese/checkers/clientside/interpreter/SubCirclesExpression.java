package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

public class SubCirclesExpression extends AbstractCirclesExpression {

	public SubCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	@Override
	public void interpreteQueries(String[] queries) {
		for (int i = 2; i < queries.length; i++) {
			visualBoard.mark(Integer.parseInt(queries[i]),
					CogTypes.valueOf(queries[1]), false);
		}
	}

}
