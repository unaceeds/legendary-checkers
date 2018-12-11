package wall.chinese.checkers.clientside.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.PrintWriter;

import javax.swing.JPanel;

import wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression;
import wall.chinese.checkers.clientside.interpreter.CirclesExpressions;

@SuppressWarnings("serial")
public class VisualBoard extends JPanel implements MouseListener {

	private static final int MAGIC = 4;

	private CogTypes myCogType;
	private Circle[] circles;
	private PrintWriter output;

	private static int[] colsInRows() {
		int[] cols = new int[4 * MAGIC + 1];
		for (int row = 0; row < MAGIC; row++)
			cols[row] = row + 1;
		for (int row = MAGIC; row < 2 * MAGIC + 1; row++)
			cols[row] = MAGIC * 3 + 1 - (row - MAGIC);
		for (int row = 2 * MAGIC + 1; row < 3 * MAGIC + 1; row++)
			cols[row] = MAGIC * 2 + 1 + (row - 2 * MAGIC);
		for (int row = 3 * MAGIC + 1; row < 4 * MAGIC + 1; row++)
			cols[row] = MAGIC - (row - 3 * MAGIC - 1);
		return cols;
	}

	public VisualBoard(PrintWriter output) {
		super();
		buildBoard();
		addMouseListener(this);
		setBackground(new Color(29, 29, 29));
		setPreferredSize(
				new Dimension(13 * 40, (int) (17 * 40 * Math.sqrt(3) / 2)));
		this.output = output;
	}

	private void buildBoard() {
		circles = new Circle[121];
		int index = 0;
		int[] cols = colsInRows();
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols[row]; col++) {
				circles[index++] = new Circle(
						13 * 20 - cols[row] * 20 + col * 40, // x
						40 * (row) * Math.sqrt(3) / 2 // y
				);
			}
		}
	}

	private void drawBoard(Graphics2D g2) {
		for (int i = 0; i < 121; i++) {
			g2.setPaint(circles[i].getPaint());
			g2.drawString(Integer.toString(i), (int) circles[i].getX(),
					(int) circles[i].getY());
			if (circles[i].getFilled()) {
				g2.fill(circles[i]);
			} else {
				g2.draw(circles[i]);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		drawBoard(g2);
	}

	public void mark(int index, CogTypes cogType, boolean fill) {
		circles[index].setFilled(fill);
		circles[index].setCogType(cogType);
		repaint();
	}

	public void interprete(String query) {
		if (query == null)
			return;
		String[] queries = query.split(" ");
		AbstractCirclesExpression expr = CirclesExpressions.valueOf(queries[0])
				.getCirclesExpression(this);
		if (expr != null) {
			expr.interpreteQueries(queries);
		}
	}

	int selected = -1;

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 121; i++) {
			if (circles[i].getBounds2D().contains(e.getX(), e.getY())) {
				if (circles[i].getCogType() == myCogType
						&& circles[i].getFilled()) {
					output.println("BMOV " + getMyCogType().toString() + " "
							+ Integer.toString(i));
					selected = i;
				} else if (circles[i].getCogType() == myCogType
						&& !circles[i].getFilled()) {
					output.println("MOV " + getMyCogType().toString() + " "
							+ Integer.toString(selected) + " "
							+ Integer.toString(i));
					selected = i;
				}
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*
		 * for (int i = 0; i < 121; i++) { if
		 * (circles[i].getBounds2D().contains(e.getX(), e.getY())) {
		 * System.out.println("selected " + Integer.toString(i)); selected = i;
		 * break; } }
		 */
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/*
		 * for (int i = 0; i < 121; i++) { if
		 * (circles[i].getBounds2D().contains(e.getX(), e.getY())) { if
		 * (selected >= 0) { String query = "MOV EAX " +
		 * Integer.toString(selected) + " " + Integer.toString(i);
		 * System.out.println(query); interprete(query); selected = -1; } break;
		 * } }
		 */
	}

	public CogTypes getMyCogType() {
		return myCogType;
	}

	public void setMyCogType(CogTypes myCogType) {
		this.myCogType = myCogType;
	}

}