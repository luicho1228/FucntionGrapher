package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.function.DoubleUnaryOperator;

/**
 * Draws a mathematical function y = f(x) onto a 2D coordinate system.
 * The function is supplied as a {@link DoubleUnaryOperator}, allowing any
 * arbitrary function to be drawn generically.
 */
public class FunctionDrawer {

	/** Adjacent samples further apart than this many pixels are not connected (asymptotes). */
	private static final int MAX_JUMP_PIXELS = 5000;
	private static final double MAX_COORD = 1_000_000;

	private DoubleUnaryOperator function;
	private final Color color;
	private final int strokeWidth;

	/**
	 * Creates a FunctionDrawer with the default style (red, stroke width 3).
	 *
	 * @param function the mathematical function y = f(x) to draw
	 */
	public FunctionDrawer(DoubleUnaryOperator function) {
		this(function, Color.RED, 3);
	}

	/**
	 * Creates a FunctionDrawer with a custom color and stroke width.
	 *
	 * @param function the mathematical function y = f(x) to draw
	 * @param color the color of the drawn curve
	 * @param strokeWidth the line width in pixels
	 */
	public FunctionDrawer(DoubleUnaryOperator function, Color color, int strokeWidth) {
		this.function = function;
		this.color = color;
		this.strokeWidth = strokeWidth;
	}

	/**
	 * Replaces the function to draw (written in axis units, y = f(x)).
	 */
	public void setFunction(DoubleUnaryOperator function) {
		this.function = function;
	}

	/**
	 * Draws the function across the visible viewport.
	 *
	 * @param g2d the graphics context to draw on
	 * @param camera the camera for world-to-screen coordinate conversion
	 * @param viewportLeft the left edge of the visible world area
	 * @param viewportRight the right edge of the visible world area
	 * @param frameWidth the width of the frame in screen pixels
 	 */
	public void draw(Graphics2D g2d, Camera camera, double viewportLeft, double viewportRight, int frameWidth) {
		double worldPerScreenWidthPixel = (viewportRight - viewportLeft) / frameWidth;

		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(strokeWidth));

		final double u = CoordinateSystem.UNIT;
		int opx = 0, opy = 0;
		boolean havePrevious = false;

		for (double x = viewportLeft; x < viewportRight; x += worldPerScreenWidthPixel) {
			// The function is written in axis units (y = f(x)); convert to/from world units here.
			// World Y points down, hence the minus sign.
			double y = -u * function.applyAsDouble(x / u);

			// Undefined here (sqrt of a negative, log of 0, 1/0...): leave a gap
			if (!Double.isFinite(y)) {
				havePrevious = false;
				continue;
			}

			Vector screen = camera.worldToScreen(new Vector(x, y));
			int px = (int) Math.round(clamp(screen.x));
			int py = (int) Math.round(clamp(screen.y));

			// Don't connect across a jump such as the asymptote of tan(x) or 1/x
			if (havePrevious && Math.abs(py - opy) <= MAX_JUMP_PIXELS) {
				g2d.drawLine(opx, opy, px, py);
			}
			opx = px;
			opy = py;
			havePrevious = true;
		}
	}

	private static double clamp(double v) {
		return Math.max(-MAX_COORD, Math.min(MAX_COORD, v));
	}
}
