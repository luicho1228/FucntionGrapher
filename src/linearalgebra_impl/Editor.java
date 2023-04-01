package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {
	/*int tileSize;
	float offsetX = 0.0f;
	float offsetY = 0.0f;
	float startPanX = 0.0f;
	float startPanY = 0.0f;
	float mouseX;
	float mouseY;
	float scaleX = 1.0f;
	float scaleY = 1.0f;
	float mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom;
	float mouseWorldX_afterZoom, mouseWorldY_afterZoom;
	JFrame frame;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth;
	int screenHeight;
	float dsx = 0.0f , dsy = 0.0f;
	float dex = 100.0f, dey = 0.0f;
	int wsx = 0, wsy = 0,wex = 0,wey = 0;
	
	public Editor(JFrame frame) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		screenWidth = frame.getWidth();
		screenHeight = frame.getHeight();
		offsetX = 0;
		offsetY = 0;
		mouseX = (float) MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (float) MouseInfo.getPointerInfo().getLocation().getY();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		setOpaque(true);
	}
	public int WorldToScreenX(float worldX, float worldY, int screenX,int screenY) {
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenX;
	}
	public int WorldToScreenY(float worldX, float worldY, int screenX,int screenY) {
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenY;
	}
	public float ScreenToWorldX(int screenX,int screenY, float worldX, float worldY ) {
		worldX = (float)(screenX) / scaleX + offsetX;
		worldY = (float)(screenY) / scaleY + offsetY;
		return worldX;
	}
	public float ScreenToWorldY(int screenX,int screenY, float worldX, float worldY ) {
		worldX = (float)(screenX) / scaleX + offsetX;
		worldY = (float)(screenY) / scaleY + offsetY;
		return worldY;
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.black);
		float dsy = this.dsy;
		float dey = this.dey;
		float dsx = this.dsx;
		float dex = this.dex;
		int wsx = this.wsx, wsy = this.wsy,wex = this.wex,wey = this.wey;
		for(float y = 0.0f; y <= frame.getHeight();y++) {
			dsx =  0.0f;
			dsy = y;
			dex = frame.getHeight();
			dey = y;
			wsx = WorldToScreenX(dsx,dsy,wsx,wsy);
			wsy = WorldToScreenY(dsx,dsy,wsx,wsy);
			wex = WorldToScreenX(dex,dey,wex,wey);
			wey = WorldToScreenY(dex,dey,wex,wey);
			g2d.drawLine(wsx, wsy, wex, wey);
		}
		for(float x = 0.0f; x <= frame.getWidth();x++) {
			dsx = x;
			dsy = 0.0f;
			dex = x;
			dey = frame.getWidth();
			wsx = WorldToScreenX(dsx,dsy,wsx,wsy);
			wsy = WorldToScreenY(dsx,dsy,wsx,wsy);
			wex = WorldToScreenX(dex,dey,wex,wey);
			wey = WorldToScreenY(dex,dey,wex,wey);
			g2d.drawLine(wsx, wsy, wex, wey);
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseWorldX_beforeZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_beforeZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		if(e.getWheelRotation() > 0) {
			scaleX *= 0.900f;
			scaleY *=  0.900f;
		}
		if(e.getWheelRotation() < 0) {
			scaleX *= 1.020f;
			scaleY *= 1.020f;
		}
		mouseWorldX_afterZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_afterZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		offsetX += (mouseWorldX_beforeZoom - mouseWorldX_afterZoom);
		offsetY += (mouseWorldY_beforeZoom - mouseWorldY_afterZoom);
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		
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
	@Override
	public void mouseDragged(MouseEvent e) {
		startPanX = (float) mouseX;
		startPanY = (float) mouseY;
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		offsetX -= (mouseX - startPanX) / scaleX;
		offsetY -= (mouseY - startPanY) / scaleY;
		startPanX = (float) mouseX;
		startPanY = (float) mouseY;
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {}*/
	//---------------------------------------------------------------------------------------------------------------------
	
	
	
	double offsetX = 0.0;
	double offsetY = 0.0;
	float startPanX = 0.0f;
	float startPanY = 0.0f;
	float mouseX;
	float mouseY;
	float scaleX = 1.0f;
	float scaleY = 1.0f;
	float mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom;
	float mouseWorldX_afterZoom, mouseWorldY_afterZoom;
	JFrame frame;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth;
	int screenHeight;
	float dsx = 0.0f , dsy = 0.0f;
	float dex = 100.0f, dey = 0.0f;
	int wsx = 0, wsy = 0,wex = 0,wey = 0;
	
	public Editor(JFrame frame) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		screenWidth = frame.getWidth();
		screenHeight = frame.getHeight();
		offsetX = (screenWidth / 2) / scaleX;
		offsetY = (screenHeight / 2) / scaleY;
		mouseX = (float) MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (float) MouseInfo.getPointerInfo().getLocation().getY();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		setOpaque(true);
	}
	public int WorldToScreenX(double worldX, double worldY, int screenX,int screenY) {
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenX;
	}
	public int WorldToScreenY(double worldX, double worldY, int screenX,int screenY) {
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenY;
	}
	public float ScreenToWorldX(int screenX,int screenY, float worldX, float worldY ) {
		worldX = (float) ((screenX) / scaleX + offsetX);
		worldY = (float) ((screenY) / scaleY + offsetY);
		return worldX;
	}
	public float ScreenToWorldY(int screenX,int screenY, float worldX, float worldY ) {
		worldX = (float) ((screenX) / scaleX + offsetX);
		worldY = (float) ((screenY) / scaleY + offsetY);
		return worldY;
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.black);
		double screenY1 = this.dsy;
		double screenY2 = this.dey;
		double screenX1 = this.dsx;
		double screenX2 = this.dex;
		int worldX1 = this.wsx, worldY1 = this.wsy,worldX2 = this.wex,worldY2 = this.wey;
		for(double y = 0.0; y <= frame.getHeight();) {
			screenX1 =  0.0;
			screenY1 = y;
			screenX2 = frame.getWidth();
			screenY2 = y;
			worldX1 = WorldToScreenX(screenX1,screenY1,worldX1,worldY1);
			worldY1 = WorldToScreenY(screenX1,screenY1,worldX1,worldY1);
			worldX2 = WorldToScreenX(screenX2,screenY2,worldX2,worldY2);
			worldY2 = WorldToScreenY(screenX2,screenY2,worldX2,worldY2);
			g2d.drawLine(worldX1, worldY1, worldX2, worldY2);
			y += 50;
		}
		for(double x = 0.0; x <= frame.getWidth();) {
			screenX1 = x;
			screenY1 = 0.0;
			screenX2 = x;
			screenY2 = frame.getHeight();
			worldX1 = WorldToScreenX(screenX1,screenY1,worldX1,worldY1);
			worldY1 = WorldToScreenY(screenX1,screenY1,worldX1,worldY1);
			worldX2 = WorldToScreenX(screenX2,screenY2,worldX2,worldY2);
			worldY2 = WorldToScreenY(screenX2,screenY2,worldX2,worldY2);
			g2d.drawLine(worldX1, worldY1, worldX2, worldY2);
			x += 50;
		}
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseWorldX_beforeZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_beforeZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		if(e.getWheelRotation() > 0) {
			scaleX *= 0.900f;
			scaleY *=  0.900f;
		}
		if(e.getWheelRotation() < 0) {
			scaleX *= 1.020f;
			scaleY *= 1.020f;
		}
		mouseWorldX_afterZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_afterZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		offsetX += (mouseWorldX_beforeZoom - mouseWorldX_afterZoom);
		offsetY += (mouseWorldY_beforeZoom - mouseWorldY_afterZoom);
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		
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
	@Override
	public void mouseDragged(MouseEvent e) {
		startPanX = (float) mouseX;
		startPanY = (float) mouseY;
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		offsetX -= (mouseX - startPanX) / scaleX;
		offsetY -= (mouseY - startPanY) / scaleY;
		startPanX = (float) mouseX;
		startPanY = (float) mouseY;
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	
	//---------------------------------------------------------------------------------------------------------------------
	/*
	 * 
	int tileSize;
	int offsetX = 0;
	int offsetY = 0;
	int startPanX = 0;
	int startPanY = 0;
	int mouseX;
	int mouseY;
	
	int scaleX = 1;
	int scaleY = 1;
	int mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom;
	int mouseWorldX_afterZoom, mouseWorldY_afterZoom;
	JFrame frame;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth;
	int screenHeight;
	
	int dsx = 0 , dsy = 0;
	int dex = 100, dey = 0;
	
	int wsx = 0, wsy = 0,wex = 0,wey = 0;
	
	public Editor(JFrame frame) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		screenWidth = frame.getWidth();
		screenHeight = frame.getHeight();
		offsetX = -screenWidth / 2;
		offsetY = -screenHeight/ 2;
		
		mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		setOpaque(true);
	}
	
	
	public int WorldToScreenX(int worldX, int worldY, int screenX,int screenY) {
		
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenX;
	}
	public int WorldToScreenY(int worldX, int worldY, int screenX,int screenY) {
		
		screenX = (int)((worldX - offsetX) * scaleX);
		screenY = (int)((worldY - offsetY) * scaleY);
		return screenY;
	}
	
	public int ScreenToWorldX(int screenX,int screenY, int worldX, int worldY ) {
		worldX = (screenX) / scaleX + offsetX;
		worldY = (screenY) / scaleY + offsetY;
		return worldX;
	}
	
	public int ScreenToWorldY(int screenX,int screenY, int worldX, int worldY ) {
		worldX = (screenX) / scaleX + offsetX;
		worldY = (screenY) / scaleY + offsetY;
		return worldY;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(Color.black);
		//System.out.println("offsets are: " + offsetX + ", " + offsetY);
		int dsy = this.dsy;
		int dey = this.dey;
		int dsx = this.dsx;
		int dex = this.dex;
		int wsx = this.wsx, wsy = this.wsy,wex = this.wex,wey = this.wey;
		//10 horizontal lines
		for(int y = 0; y <= frame.getHeight();y++) {
			dsx =  0;
			dsy = y;
			dex = frame.getHeight();
			dey = y;
			wsx = WorldToScreenX(dsx,dsy,wsx,wsy);
			wsy = WorldToScreenY(dsx,dsy,wsx,wsy);
			wex = WorldToScreenX(dex,dey,wex,wey);
			wey = WorldToScreenY(dex,dey,wex,wey);
			//drawLine
			g2d.drawLine(wsx, wsy, wex, wey);
		}
		for(int x = 0; x <= frame.getWidth();x++) {
			dsx = x;
			dsy = 0;
			dex = x;
			dey = frame.getWidth();
			
			
			wsx = WorldToScreenX(dsx,dsy,wsx,wsy);
			wsy = WorldToScreenY(dsx,dsy,wsx,wsy);
			wex = WorldToScreenX(dex,dey,wex,wey);
			wey = WorldToScreenY(dex,dey,wex,wey);
			//System.out.println(wsx);
			//drawLine
			g2d.drawLine(wsx, wsy, wex, wey);
		}
		//g2d.fillOval((int)(mouseX),(int)(mouseY), 100, 100);
		//g2d.drawLine(0, 0, this.getWidth(), this.getHeight());
	}
	
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseWorldX_beforeZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_beforeZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		//zooming out
		if(e.getWheelRotation() > 0) {
			scaleX *= 0.900f;
			scaleY *=  0.900f;
		}
		//zooming in
		if(e.getWheelRotation() < 0) {
			scaleX *= 1.020f;
			scaleY *= 1.020f;
		}
		mouseWorldX_afterZoom = ScreenToWorldX((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		mouseWorldY_afterZoom = ScreenToWorldY((int)mouseX, (int)mouseY,  mouseWorldX_beforeZoom ,mouseWorldY_beforeZoom);
		offsetX += (mouseWorldX_beforeZoom - mouseWorldX_afterZoom);
		offsetY += (mouseWorldY_beforeZoom - mouseWorldY_afterZoom);
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		startPanX =  mouseX;
		startPanY =  mouseY;
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		//System.out.println("startPanX is: " + startPanX);
		//mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		//mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		offsetX -= (mouseX - startPanX) / scaleX;
		offsetY -= (mouseY - startPanY) / scaleY;
		//System.out.println("offsetX is: " + offsetX);
		startPanX = mouseX;
		startPanY = mouseY;
		//System.out.println(offsetX + ", " + offsetY);
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 */
	
	

}
