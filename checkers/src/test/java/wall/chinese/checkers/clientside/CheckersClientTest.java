/**
 * 
 */
package wall.chinese.checkers.clientside;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Adam
 *
 */
public class CheckersClientTest {

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.CheckersClient#CheckersClient()}.
	 * 
	 * @throws IOException
	 */
	@Test(expected = Exception.class)
	public void testCheckersClient() throws IOException {
		CheckersClient objectUnderTest = new CheckersClient();
	}

}
