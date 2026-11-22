package utils;

/**
 * Represents the camera/viewpoint for a 2D coordinate system.
 * Manages position, zoom level, and viewport center for pan and zoom operations.
 */
public class Camera {

 private static final int RESET_ANIMATION_DURATION_MS = 400;

	private Vector position;
	private Vector zoom;
	private final Vector frameCenter;
	private final Vector initialPosition;
	private javax.swing.Timer resetTimer;
	private long resetStartTime;
	private Vector resetStartPosition;
	private double resetStartZoomX;
	private double resetStartZoomY;

	/**
	 * Creates a camera centered on the given frame dimensions.
	 * @param frameWidth width of the display frame in pixels
	 * @param frameHeight height of the display frame in pixels
	 */
	public Camera(double frameWidth, double frameHeight) {
		this.position = new Vector(-frameWidth / 2.0, -frameHeight / 2.0);
		this.zoom = new Vector(1.0, 1.0);
		this.frameCenter = new Vector(frameWidth / 2.0, frameHeight / 2.0);
		this.initialPosition = new Vector(position.x, position.y);
	}

	/**
	 * Pans the camera by the given delta in world coordinates.
	 * @param deltaX horizontal movement in screen pixels divided by zoom
	 * @param deltaY vertical movement in screen pixels divided by zoom
	 */
	public void pan(double deltaX, double deltaY) {
		position = position.subtract(new Vector(deltaX, deltaY));
	}

	/**
	 * Zooms the camera by the given factors.
	 * @param zoomFactorX horizontal zoom multiplier (e.g., 1.1 for zoom in, 0.9 for zoom out)
	 * @param zoomFactorY vertical zoom multiplier
	 */
	public void zoom(double zoomFactorX, double zoomFactorY) {
		zoom = new Vector(zoom.x * zoomFactorX, zoom.y * zoomFactorY);
	}

	/**
	 * Adjusts the camera position to keep a world point fixed during zoom.
	 * Call this after zoom() to maintain the cursor's world position.
	 * @param worldBefore the world coordinate before zoom
	 * @param worldAfter the world coordinate after zoom
	 */
	public void adjustZoomPivot(Vector worldBefore, Vector worldAfter) {
		position = position.add(worldBefore.subtract(worldAfter));
	}


	/**
	 * Resets the camera to its initial position immediately.
	 */
	public void reset() {
		position = new Vector(initialPosition.x, initialPosition.y);
		zoom = new Vector(1.0, 1.0);
		if (resetTimer != null && resetTimer.isRunning()) {
			resetTimer.stop();
		}
	}

	/**
	 * Starts an animated reset of the camera position and zoom to their initial values.
	 * Animates for a fixed duration (400 ms), interpolating the zoom in log space
	 * and the origin's on-screen position in a straight line to the window center.
	 * Uses smooth easing at the start and end.
	 * At the end it snaps exactly to the initial position and zoom 1.
	 *
	 * @param repaintCallback called after each animation step to update the grid and repaint
	 */
	public void startAnimatedReset(Runnable repaintCallback) {
		// Cancel any running animation
		if (resetTimer != null && resetTimer.isRunning()) {
			resetTimer.stop();
		}

		// Record the starting state
		resetStartTime = System.currentTimeMillis();
		resetStartPosition = new Vector(position.x, position.y);
		resetStartZoomX = zoom.x;
		resetStartZoomY = zoom.y;

		resetTimer = new javax.swing.Timer(16, e -> {
			long elapsed = System.currentTimeMillis() - resetStartTime;
			double t = Math.min(1.0, (double) elapsed / RESET_ANIMATION_DURATION_MS);

			// Smooth easing: ease-in-out quad
			double eased = t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;

			// Interpolate zoom in log space (zooming is multiplicative)
			double logZoomX = Math.log(resetStartZoomX) * (1 - eased) + Math.log(1.0) * eased;
			double logZoomY = Math.log(resetStartZoomY) * (1 - eased) + Math.log(1.0) * eased;
			zoom = new Vector(Math.exp(logZoomX), Math.exp(logZoomY));

			// The origin's on-screen position moves in a straight line to the window center
			// screen = (world - position) * zoom
			// For world = (0,0): screen = -position * zoom
			// Target screen = frameCenter
			// So: position = (screen - frameCenter) / zoom
			Vector startOriginScreen = new Vector(-resetStartPosition.x * resetStartZoomX, -resetStartPosition.y * resetStartZoomY);
			Vector currentOriginScreen = startOriginScreen.subtract(frameCenter)
					.multiply(1 - eased).add(frameCenter);
			position = new Vector((currentOriginScreen.x - frameCenter.x) / zoom.x,
					(currentOriginScreen.y - frameCenter.y) / zoom.y);

			if (t >= 1.0) {
				// Snap to exact final values
				position = new Vector(initialPosition.x, initialPosition.y);
				zoom = new Vector(1.0, 1.0);
				resetTimer.stop();
			}
			repaintCallback.run();
		});
		resetTimer.start();
	}

	/**
	 * Cancels any running reset animation.
	 * Call this when the user interacts (presses or scrolls) to take over from the animation.
	 */
	public void cancelReset() {
		if (resetTimer != null && resetTimer.isRunning()) {
			resetTimer.stop();
		}
	}

	// --- Getters ---

	/** @return the current camera position */
	public Vector getPosition() {
		return position;
	}

	/** @return the current zoom level */
	public Vector getZoom() {
		return zoom;
	}

	/** @return the frame center in screen pixels */
	public Vector getFrameCenter() {
		return frameCenter;
	}

	/** @return the initial camera position */
	public Vector getInitialPosition() {
		return initialPosition;
	}

	/**
	 * Converts world coordinates to screen coordinates.
	 * @param world the world coordinate
	 * @return the screen coordinate
	 */
	public Vector worldToScreen(Vector world) {
		return CoordinateTransform.worldToScreen(world, position, zoom);
	}

	/**
	 * Converts screen coordinates to world coordinates.
	 * @param screen the screen coordinate
	 * @return the world coordinate
	 */
	public Vector screenToWorld(Vector screen) {
		return CoordinateTransform.screenToWorld(screen, position, zoom);
	}
}
