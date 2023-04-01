package linearalgebra_impl;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class CustomButton extends Button{

	int x= 500;
	int y= 500;
	int w = 100;
	int h = 35;
	int textX;
	int textY;
	int innerTextGap;
	String text = "Reset";
	
	public CustomButton() {
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.blue);
		this.setForeground(Color.white);
		this.setLabel("Reset");
		
	}
	public void paint(Graphics g) {
		System.out.println("this works");
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		g2d.setBackground(Color.BLUE);
		g2d.setColor(Color.BLUE);
	//	g2d.fillRect(x, y, w, h);
	//	g2d.setColor(Color.white);
	//	g2d.drawString(text, x + 10, y + 20);
	}
		
}
