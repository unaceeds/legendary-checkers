/**
 * 
 */
package wall.chinese.checkers.clientside.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import wall.chinese.checkers.clientside.board.CogTypes;
import wall.chinese.checkers.clientside.board.VisualBoard;

/**
 * @author Adam
 *
 */
class AbstractCirclesExpressionTest {

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#AbstractCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testAbstractCirclesExpression1() {
		AbstractCirclesExpression objectUnderTest = new AddCirclesExpression(
				null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#AbstractCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testAbstractCirclesExpression2() {
		AbstractCirclesExpression objectUnderTest = new MovCirclesExpression(
				null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#AbstractCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testAbstractCirclesExpression3() {
		AbstractCirclesExpression objectUnderTest = new MulCirclesExpression(
				null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#AbstractCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testAbstractCirclesExpression4() {
		AbstractCirclesExpression objectUnderTest = new SubCirclesExpression(
				null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#AbstractCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testAbstractCirclesExpression5() {
		AbstractCirclesExpression objectUnderTest = new YouCirclesExpression(
				null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])}.
	 */
	@Test
	void testInterpreteQueries1() {
		VisualBoard board = new VisualBoard(null);
		AbstractCirclesExpression objectUnderTest = new AddCirclesExpression(
				board);
		String[] a = { "ADD", "EAX", "0" };
		objectUnderTest.interpreteQueries(a);
		assertEquals(CogTypes.EAX, board.getCircles()[0].getCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])}.
	 */
	@Test
	void testInterpreteQueries2() {
		VisualBoard board = new VisualBoard(null);
		AbstractCirclesExpression objectUnderTest = new AddCirclesExpression(
				board);
		String[] a = { "MOV", "EAX", "0", "1" };
		objectUnderTest.interpreteQueries(a);
		assertEquals(CogTypes.EAX, board.getCircles()[0].getCogType());
		assertEquals(CogTypes.EAX, board.getCircles()[1].getCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])}.
	 */
	@Test
	void testInterpreteQueries3() {
		AbstractCirclesExpression objectUnderTest = new MulCirclesExpression(
				new VisualBoard(null));
		String[] a = { "MUL", "EAX", "0" };
		objectUnderTest.interpreteQueries(a);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])}.
	 */
	@Test
	void testInterpreteQueries4() {
		AbstractCirclesExpression objectUnderTest = new SubCirclesExpression(
				new VisualBoard(null));
		String[] a = { "SUB", "EAX", "0" };
		objectUnderTest.interpreteQueries(a);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression#interpreteQueries(java.lang.String[])}.
	 */
	@Test
	void testInterpreteQueries5() {
		AbstractCirclesExpression objectUnderTest = new YouCirclesExpression(
				new VisualBoard(null));
		String[] a = { "YOU", "EAX", "0" };
		objectUnderTest.interpreteQueries(a);
		assertNotNull(objectUnderTest);
	}

}
