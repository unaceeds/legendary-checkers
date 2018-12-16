/**
 * 
 */
package wall.chinese.checkers.clientside.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Adam
 *
 */
public class CircleTest {

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#Circle(double, double)}.
	 */
	@Test
	public void testCircle() {
		Circle objectUnderTest = new Circle(0, 0);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#getPaint()}.
	 */
	@Test
	public void testGetPaint() {
		Circle objectUnderTest = new Circle(0, 0);
		assertEquals(objectUnderTest.getPaint(), objectUnderTest.getPaint());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#setCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetCogType() {
		Circle objectUnderTest = new Circle(0, 0);
		objectUnderTest.setCogType(CogTypes.EAX);
		assertEquals(CogTypes.EAX, objectUnderTest.getCogType());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#setFilled(boolean)}.
	 */
	@Test
	public void testSetFilled() {
		Circle objectUnderTest = new Circle(0, 0);
		objectUnderTest.setFilled(true);
		assertTrue(objectUnderTest.getFilled());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#getFilled()}.
	 */
	@Test
	public void testGetFilled() {
		Circle objectUnderTest = new Circle(0, 0);
		assertFalse(objectUnderTest.getFilled());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.Circle#getCogType()}.
	 */
	@Test
	public void testGetCogType() {
		Circle objectUnderTest = new Circle(0, 0);
		assertEquals(CogTypes.EBP, objectUnderTest.getCogType());
	}

}
