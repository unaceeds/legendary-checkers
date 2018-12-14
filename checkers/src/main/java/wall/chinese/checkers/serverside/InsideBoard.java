package wall.chinese.checkers.serverside;

import java.util.ArrayList;
import java.util.List;

import wall.chinese.checkers.clientside.board.CogTypes;

public class InsideBoard 
{
	
	/**
	 * This int says about number of rows in one player section, in one triangle. (?)
	 */
	private static int MAGIC = 4;
	private List<Field> fields;
	/**
	 * List of all triangles of star, they are starting positions for players.
	 * Every list contains list of fields in given player section.
	 */
	private List<List<Field>> playerSections;
	
	public InsideBoard() {
		generateFields();
		preparePlayerSections();
	}
	
	public List<Field> getFields()
	{
		return fields;
	}
	public List<List<Field>> getPlayerSections()
	{
		return playerSections;
	}
	
	/**
	 * @param row says which row should be summed up
	 * @return number of fields in row
	 */
	private static int countInRow(int row) {
		if (row >= 0 && row < MAGIC)
			return (row + 1);
		if (row >= MAGIC && row < 2*MAGIC + 1)
			return MAGIC * 3 + 1 - (row - MAGIC);
		if (row >= 2*MAGIC + 1 && row < 3*MAGIC + 1)
			return MAGIC * 2 + 1 + (row - 2*MAGIC);
		if (row >= 3*MAGIC + 1 && row < 4*MAGIC + 1)
			return MAGIC - (row - 3*MAGIC - 1);
		return 0;
	}
	/**
	 * Sets initial state of fields.
	 */
	private void generateFields() {
		fields = new ArrayList<Field>();
		for (int row = 0; row < 4*MAGIC + 1; row++) {
			for (int col = 0; col < countInRow(row); col++) {
				Field field = new Field(row);
				fields.add(field);
				setNeighboursFor(field);
			}
		}
	}
	
	
	/**
	 * Instantiates and fills every playerSection.
	 */
	private void preparePlayerSections() {
		int oneSum = MAGIC*(MAGIC+1)/2;
		playerSections = new ArrayList<List<Field>>();
		for (int i = 0; i < 6; i++) {
			playerSections.add(new ArrayList<Field>());
		}
		preparePlayerSection(0, 0, fields.get(oneSum - 1));
		preparePlayerSection(1, 0, fields.get(oneSum + 2*MAGIC + 1));
		int twoSum = (2*MAGIC+1)*(2*MAGIC+1+1)/2;
		preparePlayerSection(2, 0, fields.get(2*oneSum + twoSum + 2*MAGIC + 1));
		int threeSum = (3*MAGIC+1)*(3*MAGIC+1+1)/2;
		preparePlayerSection(3, 0, fields.get(2*oneSum + threeSum));
		preparePlayerSection(4, 0, fields.get(2*oneSum + twoSum));
		preparePlayerSection(5, 0, fields.get(oneSum + MAGIC - 1));
	}
	
	
	/**
	 * Fills one of player section with fields.
	 * @param section number of section, sorted as {@link CogTypes}
	 * @param j 
	 * @param field
	 */
	private void preparePlayerSection(int section, int j, Field field) {
		playerSections.get(section).add(field);
		//playerSections.get(section).get(playerSections.get(section).size()-1).setFill(true);
		//fields.get(field).setFill(true);
		switch (section) {
			case 0:
				if (field.getNeighbours()[Direction.LEFT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LEFT.ordinal()]));
				}
				if (field.getNeighbours()[Direction.UPPER_LEFT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.UPPER_LEFT.ordinal()]));
				}
				break;
			case 1:
				if (field.getNeighbours()[Direction.RIGHT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.RIGHT.ordinal()]));
				}
				if (field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()]));
				}
				break;
			case 2:
			case 4:
				if (field.getNeighbours()[Direction.LOWER_LEFT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LOWER_LEFT.ordinal()]));
				}
				if (field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()]));
				}
				break;
			case 3:
				if (field.getNeighbours()[Direction.RIGHT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.RIGHT.ordinal()]));
				}
				if (field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LOWER_RIGHT.ordinal()]));
				}
				break;
			case 5:
				if (field.getNeighbours()[Direction.LEFT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LEFT.ordinal()]));
				}
				if (field.getNeighbours()[Direction.LOWER_LEFT.ordinal()] >= 0) {
					preparePlayerSection(section, j++, fields.get(field.getNeighbours()[Direction.LOWER_LEFT.ordinal()]));
				}
				break;
		}
	}
	
	/**
	 * Fills neighbours array with correct indices.
	 * @param field Field, for which neighbours should be set.
	 */
	private void setNeighboursFor(Field field) {
		int index = fields.indexOf(field);
		int row = field.getRow();
		
		//for every row left and right
		if (index - 1 >= 0 && fields.get(index - 1).getRow() == row) {
			field.setNeighbour(Direction.LEFT, index - 1);
			fields.get(index - 1).setNeighbour(Direction.RIGHT, index);
		}
		
		//for rows where next col counts are bigger than previous
		if ((row >= 0 && row < MAGIC) || (row >= 2*MAGIC + 1 && row < 3*MAGIC + 1)) {
			setNeighbourFor(field, row);
		}
		
		//for magic rows
		if (row == MAGIC || row == 3*MAGIC + 1) {
			setNeighbourFor(field, 2*MAGIC);
		}
		
		//for rows where next col counts are lower than previous
		if ((row > MAGIC && row < 2*MAGIC + 1) || (row > 3*MAGIC + 1 && row < 4*MAGIC + 1)) {
			setNeighbourFor(field, 4*MAGIC + 1 - row);
		}
	}
	
	/**
	 * @param field Field, for which neighbour should be set.
	 * @param neighbourIndex Index of neighbour that should be set for field.
	 */
	private void setNeighbourFor(Field field, int neighbourIndex) {
		int index = fields.indexOf(field);
		int row = field.getRow();
		if (index - neighbourIndex - 1 >= 0 && fields.get(index - neighbourIndex - 1).getRow() == row - 1) {
			field.setNeighbour(Direction.UPPER_LEFT, index - neighbourIndex - 1);
			fields.get(index - neighbourIndex - 1).setNeighbour(Direction.LOWER_RIGHT, index);
		}
		if (index - neighbourIndex >= 0 && fields.get(index - neighbourIndex).getRow() == row - 1) {
			field.setNeighbour(Direction.UPPER_RIGHT, index - neighbourIndex);
			fields.get(index - neighbourIndex).setNeighbour(Direction.LOWER_LEFT, index);
		}
	}

	/**
	 * Filled proper player sections with proper cogTypes.
	 * @param cogTypes Array representing cogTypes of players.
	 */
	public void fillStartedBoard(CogTypes[] cogTypes) 
	{
		for(int i = 0; i < cogTypes.length; i++)
			for(int j = 0; j < playerSections.get(cogTypes[i].ordinal()).size(); j++)
				playerSections.get(cogTypes[i].ordinal()).get(j).setCogType(cogTypes[i]);
	}
	
	/**
	 * Moves pawn on board.
	 * @param cogType cogType of player who wants to move 
	 * @param oldFieldIndex index of field that player starts from
	 * @param newFieldIndex index of field that player ends on
	 */
	public void move(CogTypes cogType, int oldFieldIndex, int newFieldIndex)
	{
		fields.get(oldFieldIndex).setCogType(CogTypes.EBP);
		fields.get(newFieldIndex).setCogType(cogType);
	}
	
	/**
	 * @param cogType cogType of player
	 * @param oppCogType 
	 * @param fieldIndex index of field for which player wants to see possible moves
	 * @param afterJump false if it is after 'normal' move, true if it is after 'jump' over another pawn
	 * @return list of indices of fields on which moves are possible
	 */
	public List<Integer> getPossibleMoves(CogTypes cogType, CogTypes oppCogType, int fieldIndex, boolean afterJump)
	{//TODO use oppCogType
		List<Integer> possibleMoves = new ArrayList<Integer>();
		if(fields.get(fieldIndex).getCogType() != cogType) // pawn is not ours
			return possibleMoves;
		if(afterJump == false)
		{
			for(int i = 0; i < fields.get(fieldIndex).getNeighbours().length; i++)
			{
				if(fields.get(fieldIndex).getNeighbours()[i] != -1) //neighbour exists
				{	//neighbour is not occupied
					if(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getCogType() == CogTypes.EBP)
					{	// can't move out of opponent section
						if(!isInPlayerSection(fieldIndex, oppCogType.ordinal()) ||
								isInPlayerSection(fields.get(fieldIndex).getNeighbours()[i], oppCogType.ordinal()))
							possibleMoves.add(fields.get(fieldIndex).getNeighbours()[i]);
					}
					//neighbour is occupied
					else
					{	
						if(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i] != -1 &&
								fields.get(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i])
								.getCogType() == CogTypes.EBP ) //neighbour of neighbour exists and isn't occupied
						{	// can't move out of opponent section
							if(!isInPlayerSection(fieldIndex, oppCogType.ordinal()) ||
									isInPlayerSection(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i], 
											oppCogType.ordinal()))
							possibleMoves.add(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i]);
						}
							
					}
				}

			}
		}
		else
		{
			for(int i = 0; i < fields.get(fieldIndex).getNeighbours().length; i++)
			{
				if(fields.get(fieldIndex).getNeighbours()[i] != -1) //neighbour exists
				{	//neighbour is occupied
					if(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getCogType() != CogTypes.EBP)
					{	//neighbour of neighbour exists and isn't occupied
						if(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i] != -1 &&
								fields.get(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i])
								.getCogType() == CogTypes.EBP)
						{	// can't move out of opponent section
							if(!isInPlayerSection(fieldIndex, oppCogType.ordinal()) ||
									isInPlayerSection(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i], 
											oppCogType.ordinal()))
							possibleMoves.add(fields.get(fields.get(fieldIndex).getNeighbours()[i]).getNeighbours()[i]);
						}
					}
				}
			}
		}
		
		return possibleMoves;
	}
	/**
	 * @param oldFieldIndex index of field that player starts from
	 * @param newFieldIndex index of field that player ends on
	 * @return true if move was 'jump' over another pawn, false if move was 'normal' 
	 */
	public boolean wasJumped(int oldFieldIndex, int newFieldIndex)
	{
		if(oldFieldIndex == newFieldIndex)
			return false;
		List<Field> fields = getFields();
		Field oldField = fields.get(oldFieldIndex);
		for(int i = 0; i < oldField.getNeighbours().length; i++)
		{	//check if move was only to close neighbour or somewhere further
			if(oldField.getNeighbours()[i] == newFieldIndex)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * @return true if given playerSection contains given field, false otherwise
	 */
	private boolean isInPlayerSection(int fieldIndex, int playerSectionIndex)
	{
		for(int i = 0; i < playerSections.get(playerSectionIndex).size(); i++)
		{
			if(fields.get(fieldIndex) == playerSections.get(playerSectionIndex).get(i))
				return true;
		}
		return false;
	}
}
