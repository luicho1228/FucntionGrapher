package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class UICoordinateSystem {

	protected int centerX;
	protected int centerY;
	protected JFrame frame;
	int mouseX;
	int mouseY;
	int spaceFactorX;
	int spaceFactorY;
	int factor;
	double count =3.0;
	boolean mouseAct = false;
	boolean isZoomingIn = false;
	boolean isZoomingOut = false;
	int vx1 = 0;
	int vx2 = 0;
	int vy1 = 0;
	int vy2 = 0;
	int hx1 = 0;
	int hx2 = 0;
	int hy1 = 0;
	int hy2 = 0;
	
	public UICoordinateSystem(JFrame frame, int centerX, int centerY, int mouseX, int mouseY, int spaceFactorX, int spaceFactorY, int factor) {
		this.frame = frame;
		this.centerX = centerX;
		this.centerY = centerY;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.spaceFactorX = spaceFactorX;
		this.spaceFactorY = spaceFactorY;
		this.factor = factor;
		
		
		vx1 = centerX;
		vx2 = vx1;
		vy1 = 0;
		vy2 = frame.getHeight();
		hx1 = 0;
		hx2 = frame.getWidth();
		hy1 = centerY;
		hy2 =hy1;
		
	}
	
	public void setMouse() {
		mouseAct = true;
	}
	
	public void setCenterX(int x) {
		
	}
	
	public void setCenterY(int y) {
		
	}
	
	public void update(int mouseX, int mouseY) {
		this.centerX = mouseX;
		this.centerY = mouseY;
	}
	
	
	public void zoomOut() {
		this.vx1 -= factor/100 *4;
		this.vx2 = vx1;
		this.hy1 -= factor/100 *4;
		this.hy2 = hy1;
		/*g2d.drawLine(centerX + spaceFactorX, 0, centerX + spaceFactorX,frame.getHeight());
		g2d.drawLine(0, centerY + spaceFactorY, frame.getWidth(),centerY + spaceFactorY);
		
		spaceFactorX += count/2;
		spaceFactorY += count/2;*/
	}
	
	public void zoomIn() {
		this.vx1 += factor /100 *4;
		this.vx2 = vx1;
		this.hy1 += factor /100 *4;
		this.hy2 = hy1;
		/*g2d.drawLine(centerX - spaceFactorX, 0, centerX - spaceFactorX,frame.getHeight());
		g2d.drawLine(0, centerY - spaceFactorY, frame.getWidth(),centerY - spaceFactorY);
		
		spaceFactorX += count/2;
		spaceFactorY += count/2;*/
	}
	
	//To do
	//Use mouseX and mouseY to move Main cross relative to the mouse location
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		
		//spaceFactorX += factor;
		//spaceFactorY += factor;
		
		g2d.drawLine(vx1, vy1, vx2, vy2);
		g2d.drawLine(hx1, hy1, hx2, hy2);
		/*if(mouseAct) {
			if(isZoomingIn) {
				zoomIn(g2d);
				isZoomingIn = false;
			}
			else if(isZoomingOut) {
				zoomOut(g2d);
			}
			
		}
		
		else {
			g2d.drawLine(centerX , 0, centerX ,frame.getHeight());
			g2d.drawLine(0, centerY , frame.getWidth(),centerY);*/
			
		/*	g2d.drawLine(centerX + spaceFactorX, 0, centerX + spaceFactorX,frame.getHeight());
			g2d.drawLine(0, centerY + spaceFactorY, frame.getWidth(),centerY + spaceFactorY);
			count++;
			spaceFactorX += count/2;
			spaceFactorY += count/2;*/
		}
			/*
			
				g2d.drawLine(centerX + spaceFactorX, 0, centerX +spaceFactorX ,frame.getHeight());
				g2d.drawLine(centerX - spaceFactorX , 0, centerX - spaceFactorX ,frame.getHeight());
				g2d.drawLine(centerX, 0, centerX,frame.getHeight());
				g2d.drawLine(0, centerY + spaceFactorY, frame.getWidth(),centerY + spaceFactorY);
				g2d.drawLine(0, centerY - spaceFactorY, frame.getWidth(),centerY - spaceFactorY);
				g2d.drawLine(0, centerY, frame.getWidth(),centerY);
				g2d.drawLine(centerX, 0, centerX,frame.getHeight());
				g2d.drawLine(0, centerY, frame.getWidth(),centerY);
				
		*/
			
		
		//g2d.drawLine(centerX, 0, centerX,frame.getHeight());
		//g2d.drawLine(0, centerY, frame.getWidth(),centerY);
	//}
}
