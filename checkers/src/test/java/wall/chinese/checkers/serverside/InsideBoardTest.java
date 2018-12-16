/**
 * 
 */
package wall.chinese.checkers.serverside;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		List<Field> list = objectUnderTest.getFields();
		assertEquals(121, list.size());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#getPlayerSections()}.
	 */
	@Test
	public void testGetPlayerSections() {
		InsideBoard objectUnderTest = new InsideBoard();
		List<List<Field>> list = objectUnderTest.getPlayerSections();
		assertEquals(6, list.size());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#fillStartedBoard(wall.chinese.checkers.clientside.board.CogTypes[])}.
	 */
	@Test
	public void testFillStartedBoard() {
		InsideBoard objectUnderTest = new InsideBoard();
		CogTypes[] cogTypes = { CogTypes.EAX };
		objectUnderTest.fillStartedBoard(cogTypes);
		assertEquals(CogTypes.EAX,
				objectUnderTest.getFields().get(0).getCogType());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#move(wall.chinese.checkers.clientside.board.CogTypes, int, int)}.
	 */
	@Test
	public void testMove() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.move(CogTypes.EAX, 0, 1);
		assertEquals(CogTypes.EAX,
				objectUnderTest.getFields().get(1).getCogType());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.InsideBoard#getPossibleMoves(wall.chinese.checkers.clientside.board.CogTypes, wall.chinese.checkers.clientside.board.CogTypes, int, boolean)}.
	 */
	@Test
	public void testGetPossibleMoves1() {
		InsideBoard objectUnderTest = new InsideBoard();
		objectUnderTest.getFields().get(0).setCogType(CogTypes.EAX);
		objectUnderTest.getFields().get(1).setCogType(CogTypes.EAX);
		List<Integer> list = objectUnderTest.getPossibleMoves(CogTypes.EAX,
				CogTypes.EAX, 0, false);
		assertEquals(2, list.size());
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
		List<Integer> list = objectUnderTest.getPossibleMoves(CogTypes.EAX,
				CogTypes.EAX, 0, false);
		assertEquals(0, list.size());
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
		List<Integer> list = objectUnderTest.getPossibleMoves(CogTypes.EAX,
				CogTypes.EAX, 0, true);
		assertEquals(1, list.size());
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
		List<Integer> list = objectUnderTest.getPossibleMoves(CogTypes.EAX,
				CogTypes.EBX, 0, false);
		assertEquals(2, list.size());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped1() {
		InsideBoard objectUnderTest = new InsideBoard();
		assertFalse(objectUnderTest.wasJumped(0, 0));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped2() {
		InsideBoard objectUnderTest = new InsideBoard();
		assertFalse(objectUnderTest.wasJumped(0, 2));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.InsideBoard#wasJumped(int, int)}.
	 */
	@Test
	public void testWasJumped3() {
		InsideBoard objectUnderTest = new InsideBoard();
		assertTrue(objectUnderTest.wasJumped(0, 3));
	}

}
