package graph;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class CustomButton extends JButton {

	private static final int WIDTH = 100;
	private static final int HEIGHT = 35;

	public CustomButton() {
		super("Reset");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.blue);
		this.setForeground(Color.white);
	}
}
