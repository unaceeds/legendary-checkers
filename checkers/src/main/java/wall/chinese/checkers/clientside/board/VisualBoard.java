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

import wall.chinese.checkers.clientside.builders.GradientBuilder;
import wall.chinese.checkers.clientside.interpreter.AbstractCirclesExpression;
import wall.chinese.checkers.clientside.interpreter.CirclesExpressions;

/**
 * The Class VisualBoard.
 */
@SuppressWarnings("serial")
public class VisualBoard extends JPanel implements MouseListener {

	/** The Constant MAGIC. */
	private static final int MAGIC = 4;

	/** The my cog type. */
	private CogTypes myCogType;

	/** The circles. */
	private Circle[] circles;

	/**
	 * Gets the circles.
	 *
	 * @return the circles
	 */
	public Circle[] getCircles() {
		return this.circles;
	}

	/** The output. */
	private PrintWriter output;

	/** The message. */
	private String message;

	/**
	 * Cols in rows.
	 *
	 * @return the int[]
	 */
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

	/**
	 * Instantiates a new visual board.
	 *
	 * @param output the output
	 */
	public VisualBoard(PrintWriter output) {
		super();
		buildBoard();
		addMouseListener(this);
		setBackground(new Color(29, 29, 29));
		setPreferredSize(
				new Dimension(13 * 40, (int) (17 * 40 * Math.sqrt(3) / 2)));
		this.output = output;
		this.myCogType = CogTypes.EBP;
		this.message = "Waiting for players";
	}

	/**
	 * Builds the board.
	 */
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

	/**
	 * Draw board.
	 *
	 * @param g2 the g 2
	 */
	private void drawBoard(Graphics2D g2) {
		for (int i = 0; i < 121; i++) {
			g2.setPaint(circles[i].getPaint());
			/*
			 * g2.drawString(Integer.toString(i), (int) circles[i].getX(), (int)
			 * circles[i].getY());
			 */
			if (circles[i].getFilled()) {
				g2.fill(circles[i]);
			} else {
				g2.draw(circles[i]);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		drawBoard(g2);
		g2.setPaint(GradientBuilder.buildRadialGradient(10, 10,
				myCogType.getColor()));
		g2.drawString(message, 10, 10);
	}

	/**
	 * Mark.
	 *
	 * @param index   the index
	 * @param cogType the cog type
	 * @param fill    the fill
	 */
	public void mark(int index, CogTypes cogType, boolean fill) {
		circles[index].setFilled(fill);
		circles[index].setCogType(cogType);
		repaint();
	}

	/**
	 * Interprete.
	 *
	 * @param query the query
	 */
	public void interprete(String query) {
		if (query == null)
			return;
		String[] queries = query.split(" ");
		CirclesExpressions circ = CirclesExpressions.valueOf(queries[0]);
		if (circ != null) {
			AbstractCirclesExpression expr = circ.getCirclesExpression(this);
			expr.interpreteQueries(queries);
		}
	}

	/** The selected. */
	public int selected = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 121; i++) {
			if (circles[i].getBounds2D().contains(e.getX(), e.getY())) {
				if (circles[i].getCogType() == myCogType) {
					if (circles[i].getFilled()) {
						if (selected == i) {
							output.println("MOV " + getMyCogType().toString()
									+ " " + Integer.toString(selected) + " "
									+ Integer.toString(i));
							selected = -1;
						} else {
							output.println("BMOV " + getMyCogType().toString()
									+ " " + Integer.toString(i));
							selected = i;
						}
					} else {
						output.println("MOV " + getMyCogType().toString() + " "
								+ Integer.toString(selected) + " "
								+ Integer.toString(i));
						selected = i;
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		/*
		 * for (int i = 0; i < 121; i++) { if
		 * (circles[i].getBounds2D().contains(e.getX(), e.getY())) {
		 * System.out.println("selected " + Integer.toString(i)); selected = i;
		 * break; } }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
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

	/**
	 * Gets the my cog type.
	 *
	 * @return the my cog type
	 */
	public CogTypes getMyCogType() {
		return myCogType;
	}

	/**
	 * Sets the my cog type.
	 *
	 * @param myCogType the new my cog type
	 */
	public void setMyCogType(CogTypes myCogType) {
		this.myCogType = myCogType;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public String setMessage(String message) {
		this.message = message;
		return this.message;
	}

}