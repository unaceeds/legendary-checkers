package wall.chinese.checkers.clientside.interpreter;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

public class YouCirclesExpression extends AbstractCirclesExpression {

	public YouCirclesExpression(VisualBoard visualBoard) {
		super(visualBoard);
	}

	@Override
	public void interpreteQueries(String[] queries) {
		visualBoard.setMyCogType(CogTypes.valueOf(queries[1]));
	}

}
