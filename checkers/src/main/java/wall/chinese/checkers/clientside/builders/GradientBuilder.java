package wall.chinese.checkers.clientside.builders;

import java.awt.Color;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

/**
 * The Class GradientBuilder.
 */
public class GradientBuilder {

	/**
	 * Builds the radial gradient.
	 *
	 * @param x     the x
	 * @param y     the y
	 * @param color the color
	 * @return the paint
	 */
	public static Paint buildRadialGradient(double x, double y, Color color) {
		Point2D center = new Point2D.Double(x + 20, y + 20);
		float radius = 40;
		Point2D focus = new Point2D.Double(x + 8, y + 8);
		float[] dist = { 0.01f, 1.0f };
		Color[] colors = { Color.WHITE, color };
		return new RadialGradientPaint(center, radius, focus, dist, colors,
				CycleMethod.NO_CYCLE);
	}

}