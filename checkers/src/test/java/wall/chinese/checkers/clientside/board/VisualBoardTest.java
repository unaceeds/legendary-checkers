/**
 * 
 */
package wall.chinese.checkers.clientside.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.junit.Test;

public class VisualBoardTest {

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#paintComponent(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintComponentGraphics1() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.getCircles()[0].setFilled(true);
		BufferedImage bi = new BufferedImage(100, 100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		objectUnderTest.setSize(100, 100);
		objectUnderTest.paint(g2);
		g2.dispose();
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#paintComponent(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintComponentGraphics2() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		BufferedImage bi = new BufferedImage(100, 100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		objectUnderTest.setSize(100, 100);
		objectUnderTest.paint(g2);
		g2.dispose();
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testMouseClicked1() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		int x = (int) objectUnderTest.getCircles()[0].getCenterX();
		int y = (int) objectUnderTest.getCircles()[0].getCenterY();
		objectUnderTest.mouseClicked(
				new MouseEvent(objectUnderTest, 0, 0, 0, x, y, 0, false));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testMouseClicked2() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		int x = (int) objectUnderTest.getCircles()[0].getCenterX();
		int y = (int) objectUnderTest.getCircles()[0].getCenterY();
		objectUnderTest.getCircles()[0].setFilled(true);
		objectUnderTest.mouseClicked(
				new MouseEvent(objectUnderTest, 0, 0, 0, x, y, 0, false));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseClicked(java.awt.event.MouseEvent)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testMouseClicked3() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		int x = (int) objectUnderTest.getCircles()[0].getCenterX();
		int y = (int) objectUnderTest.getCircles()[0].getCenterY();
		objectUnderTest.getCircles()[0].setFilled(true);
		objectUnderTest.selected = 0;
		objectUnderTest.mouseClicked(
				new MouseEvent(objectUnderTest, 0, 0, 0, x, y, 0, false));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseEntered(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseEntered() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.mouseEntered(
				new MouseEvent(objectUnderTest, 0, 0, 0, 0, 0, 0, false));
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseExited(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseExited() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.mouseExited(
				new MouseEvent(objectUnderTest, 0, 0, 0, 0, 0, 0, false));
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mousePressed(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMousePressed() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.mousePressed(
				new MouseEvent(objectUnderTest, 0, 0, 0, 0, 0, 0, false));
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#mouseReleased(java.awt.event.MouseEvent)}.
	 */
	@Test
	public void testMouseReleased() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.mouseReleased(
				new MouseEvent(objectUnderTest, 0, 0, 0, 0, 0, 0, false));
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#VisualBoard(java.io.PrintWriter)}.
	 */
	@Test
	public void testVisualBoard() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.VisualBoard#mark(int, wall.chinese.checkers.clientside.board.CogTypes, boolean)}.
	 */
	@Test
	public void testMark() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.mark(0, CogTypes.EAX, true);
		assertEquals(CogTypes.EAX,
				objectUnderTest.getCircles()[0].getCogType());
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.VisualBoard#interprete(java.lang.String)}.
	 */
	@Test
	public void testInterprete1() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.interprete(null);
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#interprete(java.lang.String)}.
	 */
	@Test
	public void testInterprete2() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.interprete("ADD EAX 0");
		assertNotNull(objectUnderTest);
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#getMyCogType()}.
	 */
	@Test
	public void testGetMyCogType() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals(CogTypes.EBP, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType1() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.EAX);
		assertEquals(CogTypes.EAX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType2() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.EAX);
		assertEquals(CogTypes.EAX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType3() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.ECX);
		assertEquals(CogTypes.ECX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType4() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.EDX);
		assertEquals(CogTypes.EDX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType5() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.EEX);
		assertEquals(CogTypes.EEX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMyCogType(wall.chinese.checkers.clientside.board.CogTypes)}.
	 */
	@Test
	public void testSetMyCogType6() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		objectUnderTest.setMyCogType(CogTypes.EFX);
		assertEquals(CogTypes.EFX, objectUnderTest.getMyCogType());
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage1() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("EAX", objectUnderTest.setMessage("EAX"));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage2() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("EBX", objectUnderTest.setMessage("EBX"));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage3() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("ECX", objectUnderTest.setMessage("ECX"));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage4() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("EDX", objectUnderTest.setMessage("EDX"));
	}

	/**
	 * Test method for
	 * {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage5() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("EEX", objectUnderTest.setMessage("EEX"));
	}

	/**
	 * Test method for {@link wall.chinese.checkers.clientside.board.VisualBoard#setMessage(java.lang.String)}.
	 */
	@Test
	public void testSetMessage6() {
		VisualBoard objectUnderTest = new VisualBoard(null);
		assertEquals("EFX", objectUnderTest.setMessage("EFX"));
	}

}

