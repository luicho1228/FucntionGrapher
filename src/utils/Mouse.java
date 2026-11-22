package utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

/**
 * Encapsulates mouse interaction state and behavior for a graph panel.
 * Handles panning (drag), zooming (wheel), and delegates rendering updates
 * via a repaint callback on the owning component.
 */
public class Mouse implements MouseMotionListener, MouseWheelListener {

	private final Camera camera;
	private final CoordinateSystem coordinateSystem;
	private final JComponent component;

	private Vector lastMousePos;

	/**
	 * Creates a mouse handler.
	 *
	 * @param camera            the camera to pan and zoom
	 * @param coordinateSystem  the coordinate system to update grid spacing
	 * @param component         the panel to repaint after interactions
	 */
	public Mouse(Camera camera, CoordinateSystem coordinateSystem, JComponent component) {
		this.camera = camera;
		this.coordinateSystem = coordinateSystem;
		this.component = component;
		this.lastMousePos = new Vector(0, 0);
	}

	// --- Panning ---

	/** Called when the mouse button is pressed, recording the starting position. */
	public void onPressed(MouseEvent e) {
		// Cancel any running reset animation so dragging takes over
		camera.cancelReset();
		lastMousePos = new Vector(e.getXOnScreen(), e.getYOnScreen());
	}

	/** Called when the mouse is dragged, panning the camera by the delta. */
	public void onDragged(MouseEvent e) {
		Vector currentMouse = new Vector(e.getXOnScreen(), e.getYOnScreen());
		Vector delta = currentMouse.subtract(lastMousePos);
		Vector zoom = camera.getZoom();
		camera.pan(delta.x / zoom.x, delta.y / zoom.y);
		lastMousePos = new Vector(currentMouse.x, currentMouse.y);
		component.repaint();
	}

	// --- Zooming ---

	/** Called when the mouse wheel is scrolled, zooming the camera and updating grid spacing. */
	public void onWheelMoved(MouseWheelEvent e) {
		// Cancel any running reset animation so scrolling takes over
		camera.cancelReset();
		Vector mousePos = new Vector(e.getX(), e.getY());
		Vector worldBeforeZoom = camera.screenToWorld(mousePos);

		if (e.getWheelRotation() > 0) {
			// Zooming out
			camera.zoom(0.9, 0.9);
		} else if (e.getWheelRotation() < 0) {
			// Zooming in
			camera.zoom(1.1, 1.1);
		}

		Vector worldAfterZoom = camera.screenToWorld(mousePos);
		camera.adjustZoomPivot(worldBeforeZoom, worldAfterZoom);
		component.repaint();
	}

	// --- MouseMotionListener interface delegation ---

	@Override
	public void mouseDragged(MouseEvent e) {
		onDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// No action needed on mouse move
	}

	// --- MouseWheelListener interface delegation ---

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		onWheelMoved(e);
	}
}
