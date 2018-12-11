package wall.chinese.checkers.clientside.board;

import java.awt.Paint;
import java.awt.geom.Ellipse2D;

import wall.chinese.checkers.clientside.builders.GradientBuilder;

public class Circle extends Ellipse2D.Double {

	private static final long serialVersionUID = 1L;

	private CogTypes cogType;
	private boolean filled;
	private Paint paint;

	public Circle(double x, double y) {
		super(x, y, 30, 30);
		this.setCogType(CogTypes.EBP);
		filled = false;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setCogType(CogTypes cogType) {
		this.cogType = cogType;
		this.paint = GradientBuilder.buildRadialGradient(this.getX(),
				this.getY(), cogType.getColor());
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean getFilled() {
		return filled;
	}

	public CogTypes getCogType() {
		return cogType;
	}

}