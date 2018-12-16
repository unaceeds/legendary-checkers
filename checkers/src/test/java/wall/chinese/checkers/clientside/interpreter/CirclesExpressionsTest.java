/**
 * 
 */
package wall.chinese.checkers.clientside.interpreter;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Adam
 *
 */
class CirclesExpressionsTest {

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.interpreter.CirclesExpressions#getCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testGetCirclesExpression1() {
		CirclesExpressions objectUnderTest = CirclesExpressions.MOV;
		assertEquals(MovCirclesExpression.class,
				objectUnderTest.getCirclesExpression(null).getClass());

	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.CirclesExpressions#getCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testGetCirclesExpression2() {
		CirclesExpressions objectUnderTest = CirclesExpressions.MUL;
		assertEquals(MulCirclesExpression.class,
				objectUnderTest.getCirclesExpression(null).getClass());

	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.CirclesExpressions#getCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testGetCirclesExpression3() {
		CirclesExpressions objectUnderTest = CirclesExpressions.SUB;
		assertEquals(SubCirclesExpression.class,
				objectUnderTest.getCirclesExpression(null).getClass());

	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.CirclesExpressions#getCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testGetCirclesExpression4() {
		CirclesExpressions objectUnderTest = CirclesExpressions.YOU;
		assertEquals(YouCirclesExpression.class,
				objectUnderTest.getCirclesExpression(null).getClass());

	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.interpreter.CirclesExpressions#getCirclesExpression(wall.chinese.checkers.clientside.board.VisualBoard)}.
	 */
	@Test
	void testGetCirclesExpression5() {
		CirclesExpressions objectUnderTest = CirclesExpressions.ADD;
		assertEquals(AddCirclesExpression.class,
				objectUnderTest.getCirclesExpression(null).getClass());

	}

}
