package linearalgebra_impl;

import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class pivotMetrics{
	
	int gap;
	float worldTop = 0, worldLeft = 0, worldRight = 0, worldBottom = 0;
	int offsetX,offsetY;
	int mouseX, mouseY;
	float scaleX,scaleY;
	int screenX, screenY;
	float worldX, worldY;
	JFrame frame;
	int length;
	public pivotMetrics(JFrame frame) {
		this.frame = frame;
		offsetX =-frame.getWidth()/2;
		offsetY =-frame.getHeight()/2;
		scaleX = 1.0f;
		scaleY = 1.0f;
		length = 5;
	}
	
	public void worldToScreen(float worldX, float worldY) {
		screenX = (int) ((worldX - offsetX) * scaleX);
		screenY = (int) ((worldY - offsetY) * scaleY);
	}
	public void screenToWorld(int screenX, int screenY) {
		worldX = (screenX / scaleX) + offsetX;
		worldY = (screenY / scaleY) + offsetY;
	}
	public float screenToWorldX(int screenX, float worldX) {
		worldX = (screenX / scaleX) + offsetX;
		return worldX;
	}
	public float screenToWorldY(int screenY, float worldY) {
		worldY = (screenY / scaleY) + offsetY;
		return worldY;
	}
	public int worldToScreenX( float worldX, int screenX) {
		screenX = (int) ((worldX - offsetX) * scaleX);
		System.out.println("worldtoscreenX");
		return screenX;
	}
	public int worldToScreenY( float worldY, int screenY) {
		screenY = (int) ((worldY - offsetY) * scaleY);
		return screenY;
	}
	
	
	
	public void draw(Graphics2D g2d) {
		
		worldLeft = screenToWorldX(0, worldLeft);
		worldRight = screenToWorldX(frame.getWidth(), worldRight);
		worldTop = screenToWorldY(0, worldTop);
		worldBottom = screenToWorldY(frame.getHeight(), worldBottom);
		
		
		for(int y = 0; y <= worldBottom; y += gap) {
			if(y <= worldBottom && y >= worldTop) {
				float sy = y;
				float ey = y;
				
				int psy=0,pey=0;
				int psx=0,pex=0;
				psx = worldToScreenX(0,psx);
				psy = worldToScreenY(sy,psy);
				pey = worldToScreenY(ey,pey);
				g2d.drawLine(psx, psy, pex, pey);
			}
		}
	}

}
