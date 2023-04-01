package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JFrame;

public class UIMetrics {
	
	int centerX;
	int centerY;
	int spaceFactorX;
	int spaceFactorY;
	int factor;
	JFrame frame;
	int size = 10;
	
	public UIMetrics(JFrame frame,int factor ,int centerX, int centerY, int spaceFactorX, int spaceFactorY) {
		this.frame = frame;
		this.factor = factor;
		this.centerX = centerX;
		this.centerY = centerY;
		this.spaceFactorX = spaceFactorX;
		this.spaceFactorY = spaceFactorY;
	}
	
	public void update(int factor, int mouseX, int mouseY) {
		this.centerX = mouseX;
		this.centerY = mouseY;
		this.factor = factor;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		
		int centerX = this.centerX;
		int centerY = this.centerY;
		int size = this.size;
		int spaceFactorX = this.spaceFactorX;
		int spaceFactorY = this.spaceFactorY;
		int factor = this.factor;
		
		for(int i = 0; i < centerY; i++) {
			g2d.drawLine(centerX - size, centerY + spaceFactorX, centerX + size, centerY+spaceFactorX);
			g2d.drawLine(centerX - size, centerY - spaceFactorX, centerX + size, centerY-spaceFactorX);
			spaceFactorX += factor;	
		}
		for(int j = 0; j < centerX; j++) {
			g2d.drawLine(centerX + spaceFactorY, centerY - size, centerX + spaceFactorY, centerY + size);
			g2d.drawLine(centerX - spaceFactorY, centerY - size, centerX - spaceFactorY, centerY + size);
			spaceFactorY += factor;
		}
	}

}
