package wall.chinese.checkers.serverside;

import wall.chinese.checkers.clientside.board.CogTypes;

public class Field 
{
	private int row;
	private int[] neighbours;
	private CogTypes cogType;
	
	public Field(int row)
	{
		this.row = row;
		neighbours = new int[Direction.values().length];
		for (int i = 0; i < Direction.values().length; i++) {
			neighbours[i] = -1;
		}
		cogType = CogTypes.EBP;
	}
	
	public void setNeighbour(Direction direction, int neighbourIndex) {
		neighbours[direction.ordinal()] = neighbourIndex;
	}
	
	public int[] getNeighbours() {
		return neighbours;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setCogType(CogTypes cogType)
	{
		this.cogType = cogType;
	}
	public CogTypes getCogType()
	{
		return cogType;
	}
	
}

