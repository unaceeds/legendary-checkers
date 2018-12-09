package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

public class MovCirclesExpression extends AbstractCirclesExpression {

	public MovCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	@Override
	public void interpreteQueries(String[] queries) {
		visualBoard.mark(Integer.parseInt(queries[2]), CogTypes.EBP, false);
		visualBoard.mark(Integer.parseInt(queries[3]),
				CogTypes.valueOf(queries[1]), true);
	}

}
