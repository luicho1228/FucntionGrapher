package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Locale;

import graph.NumberLabel;

/**
 * Renders the visual elements of a 2D coordinate system:
 * grid lines, tick marks, number labels, axis lines, and the origin circle.
 *
 * Uses a UNIT constant (100) so that 1 "label unit" = 100 world units.
 * The grid step is chosen from the zoom level as 1, 2 or 5 × 10^n label units,
 * and labels are derived from it so they always match the grid lines.
 */
public class CoordinateSystem {

	/** 1 label unit = 100 world units. The function works in label units. */
	static final double UNIT = 100;

	private static final int ORIGIN_CIRCLE_RADIUS = 4;
	private static final int[] BASE = {1, 2, 5};

	private final int tickMarkHalfLength;

	/**
	 * Creates a coordinate system renderer.
	 *
	 * @param tickMarkHalfLength half the pixel length of each tick mark
	 */
	public CoordinateSystem(int tickMarkHalfLength) {
		this.tickMarkHalfLength = tickMarkHalfLength;
	}

	/**
	 * Computes the grid step in world units from the current zoom level.
	 * The step is always 1, 2 or 5 × 10^n label units, chosen so that
	 * grid lines stay at a comfortable screen density (~50–150 pixels apart).
	 * Labels are derived from this single value so they always match the grid.
	 *
	 * @param zoomFactor current zoom factor (e.g., camera.getZoom().x)
	 * @return the grid step in world units
	 */
	public double computeGridStep(double zoomFactor) {
		// Target: grid lines about 100 pixels apart on screen
		// world units per 100 pixels = 100 / zoomFactor
		// But we work in label units: 1 label unit = UNIT world units
		double targetLabelUnits = 100.0 / (zoomFactor * UNIT);

		// Pick the nearest nice number: 1, 2 or 5 × 10^n
		double log = Math.log10(targetLabelUnits);
		int exp = (int) Math.floor(log);
		double mantissa = Math.pow(10, log - exp);

		int b;
		if (mantissa < 1.5) b = BASE[0];  // 1
		else if (mantissa < 3.5) b = BASE[1];  // 2
		else b = BASE[2];  // 5

		return b * Math.pow(10, exp) * UNIT;
	}

	/**
	 * Draws the entire coordinate system.
	 *
	 * @param g2d            graphics context
	 * @param camera         camera for coordinate transformations
	 * @param viewportLeft   left boundary of visible world
	 * @param viewportRight  right boundary of visible world
	 * @param viewportTop    top boundary of visible world
	 * @param viewportBottom bottom boundary of visible world
	 * @param frameWidth     width of the display frame in pixels
	 * @param frameHeight    height of the display frame in pixels
	 */
	public void draw(Graphics2D g2d, Camera camera,
	                 double viewportLeft, double viewportRight,
	                 double viewportTop, double viewportBottom,
	                 int frameWidth, int frameHeight) {

		// Compute grid step from current zoom level
		double step = computeGridStep(camera.getZoom().x);

		// Fixed world position (origin) for tick marks, number labels, and center circle
		Vector fixedScreenPos = camera.worldToScreen(new Vector(0, 0));
		int fixedScreenX = (int) fixedScreenPos.x;
		int fixedScreenY = (int) fixedScreenPos.y;

		// Draw grid lines
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(1));
		drawVerticalGridLines(g2d, camera, step, viewportLeft, viewportRight, fixedScreenX, fixedScreenY, frameHeight);
		drawHorizontalGridLines(g2d, camera, step, viewportTop, viewportBottom, fixedScreenX, fixedScreenY, frameWidth);

		// Draw axis lines
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.black);

		// Vertical axis line (world x=0)
		int vSX = fixedScreenX;
		int vSY = (int) camera.worldToScreen(new Vector(0, viewportTop)).y;
		int vEY = (int) camera.worldToScreen(new Vector(0, viewportBottom)).y;
		g2d.drawLine(vSX, vSY, vSX, vEY);

		// Horizontal axis line (world y=0)
		int hSX = (int) camera.worldToScreen(new Vector(viewportLeft, 0)).x;
		int hEX = (int) camera.worldToScreen(new Vector(viewportRight, 0)).x;
		int hSY = fixedScreenY;
		g2d.drawLine(hSX, hSY, hEX, hSY);

		// Center circle at origin
		g2d.fillOval(fixedScreenX - ORIGIN_CIRCLE_RADIUS, fixedScreenY - ORIGIN_CIRCLE_RADIUS,
				ORIGIN_CIRCLE_RADIUS * 2, ORIGIN_CIRCLE_RADIUS * 2);
	}

	private void drawVerticalGridLines(Graphics2D g2d, Camera camera,
			double step, double viewportLeft, double viewportRight,
			int fixedScreenX, int fixedScreenY, int frameHeight) {
		// Start from the first grid line at or past viewportLeft
		int count = (int) Math.ceil(viewportLeft / step);
		for (double x = count * step; x <= viewportRight; x = count * step) {
			// Skip the axis line at x=0 (drawn separately)
			if (count != 0) {
				drawVerticalGridLine(g2d, camera, x, fixedScreenX, fixedScreenY, count, frameHeight);
			}
			count++;
		}
	}

	private void drawVerticalGridLine(Graphics2D g2d, Camera camera,
			double x, int fixedScreenX, int fixedScreenY, int count, int frameHeight) {
		int psx = (int) Math.round(camera.worldToScreen(new Vector(x, 0)).x);

		// Grid line
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(psx, 0, psx, frameHeight);

		// Tick mark at origin
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(psx, fixedScreenY - tickMarkHalfLength, psx, fixedScreenY + tickMarkHalfLength);

		// Number label — label value = count × (step in label units)
		double labelStep = x / count / UNIT;
		double labelValue = count * labelStep;
		String label = formatLabel(labelValue);
		new NumberLabel(fixedScreenX, fixedScreenY, psx, label, false).draw(g2d);
	}

	private void drawHorizontalGridLines(Graphics2D g2d, Camera camera,
			double step, double viewportTop, double viewportBottom,
			int fixedScreenX, int fixedScreenY, int frameWidth) {
		// Start from the first grid line at or past viewportTop
		int count = (int) Math.ceil(viewportTop / step);
		for (double y = count * step; y <= viewportBottom; y = count * step) {
			// Skip the axis line at y=0 (drawn separately)
			if (count != 0) {
				drawHorizontalGridLine(g2d, camera, y, fixedScreenX, fixedScreenY, count, frameWidth);
			}
			count++;
		}
	}

	private void drawHorizontalGridLine(Graphics2D g2d, Camera camera,
			double y, int fixedScreenX, int fixedScreenY, int count, int frameWidth) {
		int psy = (int) Math.round(camera.worldToScreen(new Vector(0, y)).y);

		// Grid line
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(0, psy, frameWidth, psy);

		// Tick mark at origin
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(fixedScreenX - tickMarkHalfLength, psy, fixedScreenX + tickMarkHalfLength, psy);

		// Number label — negate y because camera keeps world Y pointing down,
		// but labels should show positive Y going up (mathematical convention).
		double labelStep = y / count / UNIT;
		double labelValue = -count * labelStep;
		String label = formatLabel(labelValue);
		new NumberLabel(fixedScreenX, fixedScreenY, psy, label, true).draw(g2d);
	}

	/** Formats a label value, removing trailing zeros (e.g. 1.0 -> "1", 0.5 -> "0.5"). */
	private static String formatLabel(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
			return "";
		}
		if (value == Math.floor(value)) {
			return String.format(Locale.US, "%.0f", value);
		}
		// Up to 2 decimal places, trim trailing zeros
		String s = String.format(Locale.US, "%.2f", value);
		if (s.indexOf('.') >= 0) {
			s = s.replaceAll("0+$", "");
			s = s.replaceAll("\\.$", "");
		}
		return s;
	}
}
