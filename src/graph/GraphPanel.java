package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Camera;
import utils.CoordinateSystem;
import utils.ExpressionParser;
import utils.FunctionDrawer;
import utils.Mouse;
import utils.Vector;

public class GraphPanel extends JPanel implements ActionListener, MouseListener {
	@Serial
    private static final long serialVersionUID = 1L;

	private final int frameWidth;
	private final int frameHeight;
	Camera camera;
	Mouse mouse;
	CustomButton reset;
	FunctionDrawer functionDrawer;
	CoordinateSystem coordinateSystem;
	JTextField functionField;
	JLabel errorLabel;

	private static final String DEFAULT_FUNCTION = "sin(x)";
	private static final Color ERROR_BACKGROUND = new Color(255, 220, 220);

	public GraphPanel(JFrame frame) {
		this.frameWidth = frame.getWidth();
		this.frameHeight = frame.getHeight();
		camera = new Camera(frameWidth, frameHeight);
		// The function is written in axis units: peak of sin is 1, period is 2*PI, matching the grid numbers.
		functionDrawer = new FunctionDrawer(ExpressionParser.parse(DEFAULT_FUNCTION));
		coordinateSystem = new CoordinateSystem(10);
		mouse = new Mouse(camera, coordinateSystem, this);

		reset = new CustomButton();
		reset.addActionListener(this);

		// Function input: type an expression in x and press Enter
		functionField = new JTextField(DEFAULT_FUNCTION, 24);
		functionField.setToolTipText("Type a function of x, e.g. x^2 - 3, 2sin(x), 1/x  (press Enter)");
		functionField.addActionListener(this);
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
		errorLabel.setPreferredSize(new Dimension(330, 20));

		this.setBackground(Color.white);
		this.add(new JLabel("y ="));
		this.add(functionField);
		this.add(reset);
		this.add(errorLabel);
		this.addMouseMotionListener(mouse);
		this.addMouseWheelListener(mouse);
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		this.setOpaque(true);
	}

	// --- Coordinate conversion wrappers ---

	public Vector worldToScreen(double worldX, double worldY) {
		return camera.worldToScreen(new Vector(worldX, worldY));
	}

	public Vector screenToWorld(double screenX, double screenY) {
		return camera.screenToWorld(new Vector(screenX, screenY));
	}

	// --- Painting ---

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Viewport boundaries
		double viewportLeft = screenToWorld(0, 0).x;
		double viewportRight = screenToWorld(frameWidth, 0).x;
		double viewportTop = screenToWorld(0, 0).y;
		double viewportBottom = screenToWorld(0, frameHeight).y;

		// Draw coordinate system (grid, axes, tick marks, labels, origin)
		coordinateSystem.draw(g2d, camera, viewportLeft, viewportRight,
				viewportTop, viewportBottom, frameWidth, frameHeight);

		// Draw function
		functionDrawer.draw(g2d, camera, viewportLeft, viewportRight, frameWidth);
	}

	// --- Mouse listeners (MouseListener — only mousePressed is needed) ---

	@Override
	public void mousePressed(MouseEvent e) {
		mouse.onPressed(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// --- Function input ---

	/** Parses the text field and, if valid, makes it the function that is drawn. */
	private void applyFunctionText() {
		try {
			functionDrawer.setFunction(ExpressionParser.parse(functionField.getText()));
			functionField.setBackground(Color.white);
			errorLabel.setText(" ");
			errorLabel.setToolTipText(null);
		} catch (IllegalArgumentException ex) {
			// Keep drawing the previous valid function and show what is wrong
			functionField.setBackground(ERROR_BACKGROUND);
			errorLabel.setText(ex.getMessage());
			errorLabel.setToolTipText(ex.getMessage());
		}
		repaint();
	}

	// --- Button / text field actions ---

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == functionField) {
			applyFunctionText();
		} else if (e.getSource() == reset) {
			camera.startAnimatedReset(this::repaint);
		}
	}
}
