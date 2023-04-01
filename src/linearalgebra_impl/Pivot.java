package linearalgebra_impl;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pivot {

	int w;
	int h;
	int centerX;
	int centerY;
	
	
	public Pivot(int centerX, int centerY) {
		w = 10;
		h =10;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public void update(int centerX, int centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillOval(centerX - (w/2), centerY - (h/2), w, h);
	}
	
}
