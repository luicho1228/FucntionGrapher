package linearalgebra_impl;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;
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
		
		JFrame frame = new JFrame("viewport");
		frame.setSize(new Dimension(900,800));
		//Viewport v = new Viewport(frame);
		//viewportTest test = new viewportTest(frame);
		CoordinateTest ct = new CoordinateTest(frame);
		//Editor e = new Editor(frame);
		//JTable t = new JTable();
		frame.add(ct);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	
		
	}

}
