package graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Draws a number label next to a grid tick mark.
 * Labels are formatted strings, so they can represent integers or decimals (e.g. "1", "0.5").
 */
public class NumberLabel {

	private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	private static final int LEAD_GAP = 25;

	private final int screenX;
	private final int screenY;
	private final int xy;
	private final String label;
	private final boolean isY;

	/**
	 * Creates a number label.
	 *
	 * @param screenX  the screen X coordinate of the origin
	 * @param screenY  the screen Y coordinate of the origin
	 * @param xy       the screen coordinate of the grid line (X for vertical, Y for horizontal)
	 * @param label    the formatted label string to display (e.g. "1", "0.5", "-2")
	 * @param isY      true for vertical-axis labels, false for horizontal-axis labels
	 */
	public NumberLabel(int screenX, int screenY, int xy, String label, boolean isY) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.xy = xy;
		this.label = label;
		this.isY = isY;
	}

	public void draw(Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics(FONT);
		g2d.setFont(FONT);
		Rectangle2D stringBounds = fm.getStringBounds(label, g2d);

		if (isY) {
			g2d.setColor(Color.white);
			g2d.fillRect(screenX + LEAD_GAP, xy - (int) stringBounds.getHeight() + 3,
					(int) stringBounds.getWidth(), (int) stringBounds.getHeight());
			g2d.setColor(Color.black);
			g2d.drawString(label, screenX + LEAD_GAP, xy);
		} else {
			g2d.setColor(Color.white);
			g2d.fillRect(xy, (screenY + LEAD_GAP) - (int) stringBounds.getHeight() + 3,
					(int) stringBounds.getWidth(), (int) stringBounds.getHeight());
			g2d.setColor(Color.black);
			g2d.drawString(label, xy, screenY + LEAD_GAP);
		}
	}
}
