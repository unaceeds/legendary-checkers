/**
 * 
 */
package wall.chinese.checkers.clientside;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Adam
 *
 */
public class CheckersClientGUITest {

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.CheckersClientGUI#CheckersClientGUI(java.io.PrintWriter)}.
	 */
	@Test
	public void testCheckersClientGUI() {
		CheckersClientGUI objectUnderTest = new CheckersClientGUI(null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.CheckersClientGUI#getBoard()}.
	 */
	@Test
	public void testGetBoard() {
		CheckersClientGUI objectUnderTest = new CheckersClientGUI(null);
		assertNotNull(objectUnderTest.getBoard());
	}

}
