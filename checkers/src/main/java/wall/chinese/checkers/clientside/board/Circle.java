package wall.chinese.checkers.clientside.board;

import java.awt.Color;
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
		cogType = CogTypes.EBP;
		this.setColor(cogType.getColor());
		filled = false;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setColor(Color color) {
		this.paint = GradientBuilder.buildRadialGradient(this.getX(),
				this.getY(), color);
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean getFilled() {
		return filled;
	}

}