package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class viewportTest extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener{

	
	private int offsetX, offsetY;
	private int worldX, worldY;
	private int screenX, screenY;
	private int scaleX, scaleY;
	private int screenXStart, screenXEnd, screenYStart, screenYEnd;
	private JFrame frame;
	private int gap;
	private int startPanX , startPanY;
	private int mouseX, mouseY;
	//private int[] worldTopLeft , worldBottomRight;
	private wVector worldOrigin, worldDimension;
	private int mouseWorldX_beforeZoom, mouseWorldY_beforeZoom, mouseWorldX_afterZoom, mouseWorldY_afterZoom ;
	private int worldLeft, worldTop, worldRight, worldBottom;
	
	
	public viewportTest(JFrame frame){
		this.frame = frame;
		scaleX = 2;
		scaleY = 2;
		gap = 50;
		offsetX = (- frame.getWidth() / 2) / scaleX;
		offsetY = (- frame.getHeight() / 2) / scaleY;
		worldOrigin = new wVector(0,0);
		worldDimension = new wVector(0,0);
		
		worldOrigin = screenToWorldVector(0,0,worldOrigin);
		worldDimension = screenToWorldVector(frame.getWidth() ,frame.getHeight(), worldDimension);
		worldOrigin.floor();
		worldDimension.ceil();
		
		
		worldLeft = screenToWorldX(0, worldLeft);
		worldTop = screenToWorldY(0, worldTop);
		worldRight = screenToWorldX(frame.getWidth(), worldRight);
		worldBottom = screenToWorldY(frame.getHeight(), worldBottom);
		
		/*worldTopLeft = new int []{0,0};
		worldTopLeft = screenToWorldVector(0,0);
		worldBottomRight = screenToWorldVector(frame.getWidth() - 1,frame.getHeight()-1);
		
		for(int i = 0; i < worldTopLeft.length; i ++) {
			worldTopLeft[i] = (int) Math.ceil(worldTopLeft[i]);
		}
		for(int j = 0; j < worldBottomRight.length; j ++) {
			worldBottomRight[j] = (int) Math.floor(worldBottomRight[j]);
		}*/
		
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.setOpaque(true);
		
	}
	
	
	
	public int worldToScreenX( int worldX, int screenX) {
		screenX = (worldX - offsetX) * scaleX;
		return screenX;
	}
	
	public int worldToScreenY( int worldY, int screenY) {
		screenY = (worldY - offsetY) * scaleY;
		return screenY;
	}
	
	/*public int worldToScreenY(int[] vectorCoord, int worldY, int screenY) {
		screenY = (worldY - offsetY) * scaleY;
		return screenY;
	}*/
	
	
	
	public wVector screenToWorldVector(int screenX, int screenY, wVector vector) {
		vector.x = (screenX / scaleX) + offsetX;
		vector.y = (screenY / scaleY) + offsetY;
		return vector;
	}
	
	
	
	
	
	public int screenToWorldX(int screenX, int worldX) {
		worldX = (screenX / scaleX) + offsetX;
		return worldX;
	}
	public int screenToWorldY(int screenY, int worldY) {
		worldY = (screenY / scaleY) + offsetY;
		return worldY;
	}
	
	
	
	
	//------------------------------paint method-----------------------------------------------
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paint(g2d);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.gray);
		
		int screenXStart = this.screenXStart;
		int screenYStart = this.screenYStart;
		int screenXEnd = this.screenXEnd;
		int screenYEnd = this.screenYEnd;
		int gap = this.gap;
		
		worldOrigin = screenToWorldVector(0,0,worldOrigin);
		worldDimension = screenToWorldVector(frame.getWidth() ,frame.getHeight(), worldDimension);
		worldOrigin.floor();
		worldDimension.ceil();
		
		worldLeft = screenToWorldX(0, worldLeft);
		worldTop = screenToWorldY(0, worldTop);
		worldRight = screenToWorldX(frame.getWidth(), worldRight);
		worldBottom = screenToWorldY(frame.getHeight(), worldBottom);
		
		for(int y = 0; y <= worldBottom; y+=gap) {
			if(y >= worldTop && y <= worldBottom) {
				int sx = 0,sy = y,ex = worldRight,ey = y;
				int psx = 0, psy = 0, pex = 0, pey = 0;
				
				psx = worldToScreenX(sx,psx);
				psy = worldToScreenY(sy,psy);
				pex = worldToScreenX(ex,pex);
				pey = worldToScreenY(ey,pey);
				g2d.drawLine(psx, psy, pex, pey);
				
			}
			
			for(int x = 0 ; x <= worldRight; x+=gap) {
				if(x <= worldRight && x >=worldLeft) {
					int sx = x,sy = 0,ex = x,ey = worldBottom;
					int psx = 0, psy = 0, pex = 0, pey = 0;
					
					psx = worldToScreenX(sx,psx);
					psy = worldToScreenY(sy,psy);
					pex = worldToScreenX(ex,pex);
					pey = worldToScreenY(ey,pey);
					g2d.drawLine(psx, psy, pex, pey);
				}
			}
		}
		
		
		
		
		
		/*for(int y = worldOrigin.y ; y <= worldDimension.y; y += gap) {
			
			for(int x = worldOrigin.x; x <= worldDimension.x; x += gap) {
				int startX = x, startY = y;
				int endX = worldDimension.x , endY = y;
				screenXStart = worldToScreenX(startX, screenXStart);
				screenYStart = worldToScreenY(startY, screenYStart);
				screenXEnd = worldToScreenX(endX, screenXEnd);
				screenYEnd = worldToScreenY(endY, screenYEnd);
				g2d.drawLine(screenXStart, screenYStart, screenXEnd, screenYEnd);
				
				
				startX = x;
				startY = y;
				endX = x;
				endY = worldDimension.y;
				screenXStart = worldToScreenX(startX, screenXStart);
				screenYStart = worldToScreenY(startY, screenYStart);
				screenXEnd = worldToScreenX(endX, screenXEnd);
				screenYEnd = worldToScreenY(endY, screenYEnd);
				g2d.drawLine(screenXStart, screenYStart, screenXEnd, screenYEnd);

			}
		}*/
		/*g2d.setColor(Color.YELLOW);
		for(int i = worldOrigin.x; i <= screenXStart; i += gap) {
			int sx = 0, sy = 0, ex = 0,ey = 0;
			sx = worldToScreenX(i, sx);
			sy = worldToScreenY(0, sy);
			ex = worldToScreenX(i, ex);
			ey = worldToScreenY(worldDimension.x, ey);
			g2d.drawLine(sx, sy, ex, ey);
			
		}*/
		
		g2d.setColor(Color.RED);
		screenXStart = worldToScreenX(0, screenXStart);
		screenYStart = worldToScreenY(worldOrigin.y, screenYStart);
		screenXEnd = worldToScreenX(0, screenXEnd);
		screenYEnd = worldToScreenY(worldDimension.y, screenYEnd);
		g2d.drawLine(screenXStart, screenYStart, screenXEnd, screenYEnd);
		screenXStart = worldToScreenX(worldOrigin.x, screenXStart);
		screenYStart = worldToScreenY(0, screenYStart);
		screenXEnd = worldToScreenX(worldDimension.x, screenXEnd);
		screenYEnd = worldToScreenY(0, screenYEnd);
		g2d.drawLine(screenXStart, screenYStart, screenXEnd, screenYEnd);
	}
	
	
	//-------------------------------mouse events---------------------------------------------
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		startPanX =  mouseX;
		startPanY =  mouseY;
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		offsetX -= (mouseX - startPanX) / scaleX;
		offsetY -= (mouseY - startPanY) / scaleY;
		startPanX = mouseX;
		startPanY = mouseY;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
	/*	mouseX = e.getX();
		mouseY = e.getY();
		mouseWorldX_beforeZoom = screenToWorldX((int)mouseX,  mouseWorldX_beforeZoom);
		mouseWorldY_beforeZoom = screenToWorldY((int)mouseY,  mouseWorldY_beforeZoom);
		if(e.getWheelRotation() > 0) {
			scaleX *= 0.900f;
			scaleY *=  0.900f;
		}
		if(e.getWheelRotation() < 0) {
			scaleX *= 1.020f;
			scaleY *= 1.020f;
		}
		mouseWorldX_afterZoom = screenToWorldX((int)mouseX,  mouseWorldX_beforeZoom);
		mouseWorldY_afterZoom = screenToWorldY((int)mouseY, mouseWorldY_beforeZoom);
		offsetX += (mouseWorldX_beforeZoom - mouseWorldX_afterZoom);
		offsetY += (mouseWorldY_beforeZoom - mouseWorldY_afterZoom);
		repaint();*/
	}

}

class wVector{
	int x;
	int y;
	public wVector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void ceil() {
		this.x = (int) Math.ceil(x);
		this.y = (int) Math.ceil(y);
	}
	public void floor() {
		this.x = (int) Math.floor(x);
		this.y = (int) Math.floor(y);
	}
}
