/**
 * 
 */
package wall.chinese.checkers.serverside;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.Socket;

import org.junit.Test;

/**
 * @author Adam
 *
 */
public class GameTest {

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.Game#Game(int)}.
	 */
	@Test
	public void testGame1() {
		Game objectUnderTest = new Game(2);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.Game#Game(int)}.
	 */
	@Test
	public void testGame2() {
		Game objectUnderTest = new Game(3);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.Game#Game(int)}.
	 */
	@Test
	public void testGame3() {
		Game objectUnderTest = new Game(4);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.Game#Game(int)}.
	 */
	@Test
	public void testGame4() {
		Game objectUnderTest = new Game(6);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.Game#addPlayer(wall.chinese.checkers.serverside.Game.Player)}.
	 */
	@Test
	public void testAddPlayer1() {
		Game objectUnderTest = new Game(2);
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 0));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 1));
		assertEquals(2, objectUnderTest.getPlayers().length);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.Game#addPlayer(wall.chinese.checkers.serverside.Game.Player)}.
	 */
	@Test
	public void testAddPlayer2() {
		Game objectUnderTest = new Game(6);
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 0));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 1));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 2));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 3));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 4));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 5));
		assertEquals(6, objectUnderTest.getPlayers().length);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.serverside.Game#getPlayers()}.
	 */
	@Test
	public void testGetPlayers() {
		Game objectUnderTest = new Game(2);
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 0));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 1));
		assertEquals(2, objectUnderTest.getPlayers().length);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.serverside.Game#setRandomCurrentPlayer()}.
	 */
	@Test
	public void testSetRandomCurrentPlayer() {
		Game objectUnderTest = new Game(2);
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 0));
		objectUnderTest.addPlayer(objectUnderTest.new Player(new Socket(), 1));
		objectUnderTest.setRandomCurrentPlayer();
		objectUnderTest.getPlayers()[0].start();
	}

}
