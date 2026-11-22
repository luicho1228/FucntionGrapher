package graph;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
			}
		});
	}

	private static void createGUI() {
		JFrame frame = new JFrame("Function Grapher");
		frame.setSize(new Dimension(900, 800));
		GraphPanel graphPanel = new GraphPanel(frame);
		frame.add(graphPanel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
