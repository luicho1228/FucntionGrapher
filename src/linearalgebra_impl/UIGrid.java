package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public class UIGrid{
	
	int centerX;
	int centerY;
	int spaceFactorX;
	int spaceFactorY;
	int factor;
	int mouseX = 0;
	int mouseY = 0;
	int fallOffX = 0;
	int fallOffY = 0;
	int c = 0;
	JFrame frame;
	Dimension d;
	MouseLocation mouse;
	LinePoints pairs;
	LinePoints[][] pairArray;
	int tempFactor = 0;
	int pivotX;
	int pivotY;
	boolean isMouseWheel = false;
	int oldFactor = 1;;
	
	AffineTransform at;

	public UIGrid(JFrame frame ,int factor ,int centerX, int centerY, int spaceFactorX, int spaceFactorY, MouseLocation mouse) {
		this.frame = frame;
		this.factor = factor;
		this.centerX = centerX;
		this.centerY = centerY;
		this.spaceFactorX = spaceFactorX;
		this.spaceFactorY = spaceFactorY;
		this.mouse = mouse;
		pivotX = centerX;
		pivotY = centerY;
		pairs = new LinePoints(centerX, centerY);
		pairArray = new LinePoints[frame.getHeight()][frame.getWidth()];
		System.out.println("frame heigth is: "+ frame.getHeight() + "frame width is: " + frame.getWidth());
	}
	
	public void update(int factor, int mouseX, int mouseY) {
		this.factor = factor;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.tempFactor = factor;
		//this.pivotX = mouseX;
		//this.pivotY = mouseY;
		fallOffX = Math.abs(this.centerX - mouseX);
		fallOffY = Math.abs(this.centerY - mouseY);
		isMouseWheel = true;
	//	centerX = mouseX + spaceFactorX;
		//centerY = mouseY + spaceFactorY;
	}
	
	
	public void setSpaceFactorX(int sfx) {
		this.spaceFactorX = sfx;
	}
	public void setSpaceFactorY(int sfy) {
		this.spaceFactorY = sfy;
	}
	
	/*public void drawLines(Graphics2D g2d) {
		at = g2d.getTransform();
		
		if(isMouseWheel) {
			
			for(int j = 0; j < frame.getWidth() ; j++) {
				g2d.drawLine(mouseX + spaceFactorX - factor, 0,mouseY + spaceFactorX - factor,frame.getHeight());
				g2d.drawLine(0, mouseY + spaceFactorY - factor,frame.getWidth(),mouseY + spaceFactorY - factor);
				spaceFactorX += factor;
				spaceFactorY += factor;
				}
			
		}	
		else {		
			oldFactor = factor;
			for(int i = 0; i < frame.getWidth() ; i++) {
				g2d.drawLine(spaceFactorX, 0,spaceFactorX,frame.getHeight());
				g2d.drawLine(0, spaceFactorY,frame.getWidth(),spaceFactorY);
				spaceFactorX += factor;
				spaceFactorY += factor;
				}
			}*/
		
		/*for(int i = 0; i < frame.getWidth() ; i++) {
					g2d.drawLine(pivotX + spaceFactorX, 0,pivotX + spaceFactorX,frame.getHeight());
					g2d.drawLine(pivotX - spaceFactorX, 0,pivotX - spaceFactorX,frame.getHeight());
					g2d.drawLine(0, pivotY + spaceFactorY,frame.getWidth(), pivotY + spaceFactorY);
					g2d.drawLine(0, pivotY - spaceFactorY,frame.getWidth(), pivotY - spaceFactorY);
					spaceFactorX += factor;
					spaceFactorY += factor;
				}
		spaceFactorX = factor;
		spaceFactorY = factor;*/
		
	//}
	
	
	public void draw(Graphics2D g2d) {
		
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.GRAY);
		int centerY = this.centerY;
		int spaceFactorX = this.spaceFactorX;
		int factor = this.factor;
		JFrame frame = this.frame;
		int centerX = this.centerX;
		int spaceFactorY = this.spaceFactorY;
		MouseLocation mouse = this.mouse;
		int mouseY = this.mouseY;
		int mouseX = this.mouseX;
		int fallOffX = this.fallOffX;
		int c =this.c;
		int pAdd = 0;
		int pSub = 0;
		int mpAdd =0;
		int mpSub = 0;
		int fallOffY = 0;
		int tempFactor = this.tempFactor;
		int pivotX =this.pivotX;
		int pivotY = this.pivotY;
		
		int oldFactor = this.oldFactor;
		double multiplier;
	/*	if(fallOffY > 0) {
			centerY = mouseY;
			centerX = mouseX;
		}
		int y = 0;*/
	
	//	spaceFactorX += factor;
		//spaceFactorY += factor;
	//	System.out.println(centerY + ", " + centerX);
		//(int) frame.getWidth()
		
		
		//----------------------------------previous method-----------------------------------------------------
		/*System.out.println(factor);
		for(int i = 0; i < centerX; i++) {
			
			g2d.fillOval(centerX, centerY , factor, factor);
			mpAdd = mouseY + spaceFactorX;
			pAdd = centerY + spaceFactorX;
			mpSub = mouseY - spaceFactorX;
			pSub = centerY - spaceFactorX;
			int pivotXAdd = mpAdd;
			int pivotXSub = mpSub;
			System.out.println("pivot + mouse: " + pivotXAdd);
			g2d.drawLine(0,pivotXAdd+ factor , (int) frame.getWidth(), pivotXAdd + factor);
			g2d.drawLine(0, pivotXSub - factor , (int) frame.getWidth(),pivotXSub - factor);
			spaceFactorX += tempFactor;
			//System.out.println("sfx is: " + spaceFactorX);
			
		}
		
		
		for(int j = 0; j < centerY; j++) {
			mpAdd = mouseX + spaceFactorY;
			pAdd = centerX + spaceFactorY;
			mpSub = mouseX - spaceFactorY;
			pSub = centerX - spaceFactorY;
			int pivotYAdd = mpAdd;
			int pivotYSub = mpSub;
			//centerX = ( mouseX - centerX) + factor;
			//pAdd = centerX + spaceFactorY;
			g2d.drawLine( pivotYAdd + factor, 0,  pivotYAdd + factor , (int) frame.getHeight() );
		//	centerX = ( mouseX - centerX) + factor;
			//pSub = centerX - spaceFactorY;
			g2d.drawLine(pivotYSub -factor , 0, pivotYSub - factor , (int) frame.getHeight());
			spaceFactorY += tempFactor;
		
		}*/
		
		//-----------------------------------------------------------------------------------------
		
	for(int i = 0; i < centerX; i++) {
				
				g2d.fillOval(centerX, centerY , factor, factor);
				mpAdd = mouseY + spaceFactorX;
				pAdd = centerY + spaceFactorX;
				mpSub = mouseY - spaceFactorX;
				pSub = centerY - spaceFactorX;
				int pivotXAdd = mpAdd;
				int pivotXSub = mpSub;
				//System.out.println("pivot + mouse: " + pivotXAdd);
				g2d.drawLine(0,pivotXAdd+ factor , (int) frame.getWidth(), pivotXAdd + factor);
				g2d.drawLine(0, pivotXSub - factor , (int) frame.getWidth(),pivotXSub - factor);
				spaceFactorX += tempFactor;
				//System.out.println("sfx is: " + spaceFactorX);
				
			}
			
			
			for(int j = 0; j < centerY; j++) {
				mpAdd = mouseX + spaceFactorY;
				pAdd = centerX + spaceFactorY;
				mpSub = mouseX - spaceFactorY;
				pSub = centerX - spaceFactorY;
				int pivotYAdd = mpAdd;
				int pivotYSub = mpSub;
				//centerX = ( mouseX - centerX) + factor;
				//pAdd = centerX + spaceFactorY;
				g2d.drawLine( pivotYAdd + factor, 0,  pivotYAdd + factor , (int) frame.getHeight() );
			//	centerX = ( mouseX - centerX) + factor;
				//pSub = centerX - spaceFactorY;
				g2d.drawLine(pivotYSub -factor , 0, pivotYSub - factor , (int) frame.getHeight());
				spaceFactorY += tempFactor;
			
			}
			
			this.spaceFactorX = 0;
			this.spaceFactorX = 0;
		//----------------------------------------------------------------------------------------
		/*if(isMouseWheel) {
			oldFactor = factor;
			multiplier = (double)factor / oldFactor;
			for(int j = 0; j < frame.getWidth() ; j++) {
				g2d.drawLine((int) (mouseX + (spaceFactorX * multiplier)), 0,(int) (mouseX + (spaceFactorX * multiplier)),frame.getHeight());
				g2d.drawLine(0,(int) (mouseY + (spaceFactorY *multiplier)),frame.getWidth(), (int)(mouseY + (spaceFactorY * multiplier)));
				spaceFactorX += factor;
				spaceFactorY += factor;
				}
			
		//	drawLines(g2d);
			//spaceFactorX = 0;
			//spaceFactorY = 0;
		}
		else {
			
			for(int i = 0; i < frame.getWidth() ; i++) {
				g2d.drawLine(spaceFactorX, 0,spaceFactorX,frame.getHeight());
				g2d.drawLine(0, spaceFactorY,frame.getWidth(),spaceFactorY);
				spaceFactorX += factor;
				spaceFactorY += factor;
				}
			//drawLines(g2d);
		}
		isMouseWheel = false;
		
		
		//drawLines(g2d);*/
		
	//	this.spaceFactorX = 0;
		//this.spaceFactorY = 0;
		//-----------------------------------------------------------------------------------------
		/*for(int i = 0; i < frame.getWidth() ; i++) {
			g2d.drawLine(spaceFactorX, 0,spaceFactorX,frame.getHeight());
			g2d.drawLine(0, spaceFactorY,frame.getWidth(),spaceFactorY);
			spaceFactorX += factor;
			spaceFactorY += factor;
		}*/
	}
}
