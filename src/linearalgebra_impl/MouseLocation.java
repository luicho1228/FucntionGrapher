package linearalgebra_impl;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseLocation {

	PointerInfo mouseInfo;
	Point mouseLoc;
	int mouseX;
	int mouseY;
	boolean isMouseZooming = false;
	boolean isMouseInFirstQuadrant = false;
	boolean isMouseInSecondQuadrant = false;
	boolean isMouseInThridQuadrant = false;
	boolean isMouseInFourthQuadrant = false;
	int mouseOffset = 6;
	int mouseOffsetY = 30;
	JFrame frame;
	public MouseLocation(JFrame frame, JPanel panel) {
		mouseInfo = MouseInfo.getPointerInfo();
		mouseLoc = mouseInfo.getLocation();
		mouseX = mouseLoc.x  - panel.getLocation().x - mouseOffset;
		mouseY = mouseLoc.y - panel.getLocation().y - mouseOffsetY;
		this.frame = frame;
		System.out.println(mouseX + " , " + mouseY );
	}
	
	public Point getMouseLoc() {
		return mouseLoc;
		
	}
	
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	
	public void reloadMouseLocation() {
		mouseInfo = MouseInfo.getPointerInfo();
		mouseLoc = mouseInfo.getLocation();
		mouseX = mouseLoc.x - frame.getLocation().x - mouseOffset;
		mouseY = mouseLoc.y - frame.getLocation().y - mouseOffsetY;
	}
	
	
	public void setMouseZooming(Boolean isZooming) {
		isMouseZooming = isZooming;
	}
}
