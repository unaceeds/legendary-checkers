/**
 * 
 */
package wall.chinese.checkers.serverside;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import wall.chinese.checkers.clientside.board.CogTypes;

/**
 * @author Adam
 *
 */
public class InsideBoardTest {

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#InsideBoard()}.
	 */
	@Test
	public void testInsideBoard() {
		InsideBoard objectUnderTest = new InsideBoard();
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#getFields()}.
	 */
	@Test
	public void testGetFields() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields();
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#getPlayerSections()}.
	 */
	@Test
	public void testGetPlayerSections() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getPlayerSections();
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#fillStartedBoard(wall.chinese.checkers.clientside.board.CogTypes[])}.
	 */
	@Test
	public void testFillStartedBoard() {
		InsideBoard objectUnderTest = new InsideBoard();
		CogTypes[] cogTypes = { CogTypes.EAX };
		objectUnderTest.fillStartedBoard(cogTypes);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#move(wall.chinese.checkers.clientside.board.CogTypes, int, int)}.
	 */
	@Test
	public void testMove() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.move(CogTypes.EAX, 0, 1);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#getPossibleMoves(wall.chinese.checkers.clientside.board.CogTypes, wall.chinese.checkers.clientside.board.CogTypes, int, boolean)}.
	 */
	@Test
	public void testGetPossibleMoves1() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields().get(0).setCogType(CogTypes.EAX);
		objectUnderTest.getFields().get(1).setCogType(CogTypes.EAX);
		objectUnderTest.getPossibleMoves(CogTypes.EAX, CogTypes.EAX, 0, false);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#getPossibleMoves(wall.chinese.checkers.clientside.board.CogTypes, wall.chinese.checkers.clientside.board.CogTypes, int, boolean)}.
	 */
	@Test
	public void testGetPossibleMoves2() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields().get(0).setCogType(CogTypes.EBX);
		objectUnderTest.getFields().get(1).setCogType(CogTypes.EAX);
		objectUnderTest.getPossibleMoves(CogTypes.EAX, CogTypes.EAX, 0, false);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#getPossibleMoves(wall.chinese.checkers.clientside.board.CogTypes, wall.chinese.checkers.clientside.board.CogTypes, int, boolean)}.
	 */
	@Test
	public void testGetPossibleMoves3() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields().get(0).setCogType(CogTypes.EAX);
		objectUnderTest.getFields().get(1).setCogType(CogTypes.EAX);
		objectUnderTest.getPossibleMoves(CogTypes.EAX, CogTypes.EAX, 0, true);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#getPossibleMoves(wall.chinese.checkers.clientside.board.CogTypes, wall.chinese.checkers.clientside.board.CogTypes, int, boolean)}.
	 */
	@Test
	public void testGetPossibleMoves4() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields().get(0).setCogType(CogTypes.EAX);
		objectUnderTest.getFields().get(1).setCogType(CogTypes.EAX);
		objectUnderTest.getPossibleMoves(CogTypes.EAX, CogTypes.EBX, 0, true);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped1() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.wasJumped(0, 0);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped2() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.wasJumped(0, 1);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped3() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.wasJumped(0, 3);
		assertNotNull(objectUnderTest);
	}

}
