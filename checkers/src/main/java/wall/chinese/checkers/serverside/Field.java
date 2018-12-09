package wall.chinese.checkers.serverside;

public class Field 
{
	//TODO enum FieldState i pole state
	private int row;
	private int[] neighbours;
	
	public Field(int row)
	{
		this.row = row;
		neighbours = new int[Direction.values().length];
		for (int i = 0; i < Direction.values().length; i++) {
			neighbours[i] = -1;
		}
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
	
}

