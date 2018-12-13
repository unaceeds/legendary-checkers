package wall.chinese.checkers.serverside;

import wall.chinese.checkers.clientside.board.CogTypes;

/**
 * Particular field on board. This class is backend representation of circle.
 * @author piotr
 * @see Circle
 */
public class Field 
{
	
	/**
	 * One of {1,2, ..., 16, 17} row. We count rows from top to bottom on board.
	 */
	private int row;
	
	/**
	 * Array of int, corresponding with {@link Direction}, elements of array are -1 
	 * when field hasn't got neighbour on particular field or index of field in {@link InsideBoard.fields} 
	 */
	private int[] neighbours;
	private CogTypes cogType;
	
	public Field(int row)
	{
		this.row = row;
		neighbours = new int[Direction.values().length];
		for (int i = 0; i < Direction.values().length; i++) {
			neighbours[i] = -1;
		}
		cogType = CogTypes.EBP; // default fields are black
	}
	
	/**
	 * Set given neighbour (using index of field in {@link InsideBoard.fields}) in given direction.
	 * @param direction
	 * @param neighbourIndex
	 */
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

