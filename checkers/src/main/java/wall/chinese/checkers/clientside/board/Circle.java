package wall.chinese.checkers.clientside.board;

import java.awt.Paint;
import java.awt.geom.Ellipse2D;

import wall.chinese.checkers.clientside.builders.GradientBuilder;

/**
 * The Class Circle.
 */
public class Circle extends Ellipse2D.Double {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cog type. */
	private CogTypes cogType;

	/** The filled. */
	private boolean filled;

	/** The paint. */
	private Paint paint;

	/**
	 * Instantiates a new circle.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Circle(double x, double y) {
		super(x, y, 30, 30);
		this.setCogType(CogTypes.EBP);
		filled = false;
	}

	/**
	 * Gets the paint.
	 *
	 * @return the paint
	 */
	public Paint getPaint() {
		return paint;
	}

	/**
	 * Sets the cog type.
	 *
	 * @param cogType the new cog type
	 */
	public void setCogType(CogTypes cogType) {
		this.cogType = cogType;
		this.paint = GradientBuilder.buildRadialGradient(this.getX(),
				this.getY(), cogType.getColor());
	}

	/**
	 * Sets the filled.
	 *
	 * @param filled the new filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/**
	 * Gets the filled.
	 *
	 * @return the filled
	 */
	public boolean getFilled() {
		return filled;
	}

	/**
	 * Gets the cog type.
	 *
	 * @return the cog type
	 */
	public CogTypes getCogType() {
		return cogType;
	}

}