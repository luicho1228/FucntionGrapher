package linearalgebra_impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Viewport extends JPanel implements MouseMotionListener , MouseWheelListener{

	int x;
	int y;
	int centerX;
	int centerY;
	int spaceFactorX;
	int spaceFactorY;
	int factor;
	JFrame frame;
	
	int []intNum;
	double []doubleNum;
	//int [] numberTo10 = new int[10];
//	double[] numberRangeDouble = new double[10];
	int count = 1;
	double countDouble = 0.1;
	
	int crossFactor = 111;
	int mouseX;
	int mouseY;
	
	int countMult =0;
	int[] mult = new int[3];
	
	int countZoomOut = 0;
	int countZoomIn = 0;
	int countX =0;
	int countY = 0;
	
	
	boolean zoomingOut;
	boolean zoomingIn;
	
	Point mouseLoc;
	MouseLocation mouse;
	UICoordinateSystem cross;
	Pivot pivot;
	UIGrid grid;
	UIMetrics metrics;
	CoordinateSystem numbers;
	renderTest test;
	
	AffineTransform t;
	
	
	int max = 100;
	int min = 0;
	double norm = 0.5;
	int l;
	
	public Viewport(JFrame frame) {
		super();
		this.frame = frame;
		t = new AffineTransform();
		factor = 111;
		spaceFactorX = 0;
		spaceFactorY = 0;
		
		
		
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		centerX = this.getPreferredSize().width/2;
		centerY = this.getPreferredSize().height/2;
		mouse = new MouseLocation(frame, this);
		cross = new UICoordinateSystem(frame, centerX, centerY, mouse.getMouseX(), mouse.getMouseY(), spaceFactorX, spaceFactorY, crossFactor);
		pivot = new Pivot(centerX, centerY);
		grid = new UIGrid(frame, l, centerX , centerY , spaceFactorX, spaceFactorY, mouse);
		metrics= new UIMetrics(frame, factor, centerX, centerY, spaceFactorX, spaceFactorY);
		numbers = new CoordinateSystem(count,0.0, centerX , centerY,factor);
		test = new renderTest();
		crossFactor = 0;
		mouseX = mouse.mouseX;
		mouseY = mouse.mouseY;
		// System.out.println(mouse.getMouseLoc());
		//loads arrays data
		//loadArrays();
		this.setBackground(Color.WHITE);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		
	//	System.out.println((int)(2 + 2.5));
		setOpaque(true);
		
	}
	
	
	
	//creates image before drawing
	
	private Image createImage() {
		BufferedImage imageBuffer = new BufferedImage(frame.getWidth(),frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) imageBuffer.getGraphics();
	//	test.draw(g2d);
		grid.draw(g2d);
		
		//cross.draw(g2d);
		//metrics.draw(g2d);
		//pivot.draw(g2d);
		//numbers.draw(g2d);
	
		//populateNumbers();
	//	cross = new UICoordinateSystem(frame, centerX, centerY, mouse.mouseX, mouse.mouseY, spaceFactorX, spaceFactorY, factor);
		
		
		/*if(numberTo10[0] < 1) {
			
			if(numbers.isDouble == false) {
			//	System.out.println("switched to double");
				numbers.setNumbersToDoubles();
				loadDoubleArray();
				//loadIntArray();
			}
		}
		
		if(numberRangeDouble[0] >= 1.0){
			if(numbers.isDouble == true) {
			//	System.out.println("switched to int");
				numbers.setNumbersToIntegers();
				//System.out.println("are number ints? " + numbers.isDouble);
				//loads arrays data
				loadIntArray();
			}
		}*/
		
		//System.out.println("are number doubles? " + numbers.isDouble);
		
		return imageBuffer;
		
	}
	

	
	
	protected void paintComponent(Graphics g) {
		Graphics g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		Image image = createImage();
		g.drawImage(image, 0, 0, this);
		
		g2d.dispose();
	}

	
	public double lerp(double norm, int min, int max) {
		return (max - min) * norm + min;
	}
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouse.reloadMouseLocation();
		//cross.setMouse();
		//zoom in wheelRotation is negative
		if(e.getWheelRotation() < 0) {
			l = (int) lerp(norm,min,max);
			grid.update(l, mouse.getMouseX(), mouse.getMouseY());
			norm += 0.01;
			
			if(norm > 1) {
				norm = 0.5;
			}
			
			
			/*factor += -1 * (e.getWheelRotation() * 2);
			mouse.reloadMouseLocation();
			cross.setMouse();
			cross.update(mouse.getMouseX(), mouse.getMouseY());
			cross.isZoomingIn = true;
			cross.zoomIn();
			
			//System.out.println(mouse.getMouseX());
			//crossFactor += -1 * (e.getWheelRotation() * 3);
			grid.update(factor, mouse.getMouseX(), mouse.getMouseY());
			metrics.update(factor, mouse.getMouseX(), mouse.getMouseY());
			numbers.update(factor, mouse.getMouseX(), mouse.getMouseY());
			
			System.out.println(mouse.getMouseLoc());
			
			if (factor > 153) {
				factor = 111;
				numbers.decreaseCurrentIndex();
				if(!numbers.isDouble) {
					
					numbers.setIntCoordinates(numbers.coordinateIdentities[numbers.getCurrentIndex()]);
				}
				if(numbers.isDouble) {
					
					numbers.setDoubleCoordinates(numbers.decimalIdentities[numbers.getCurrentIndex()]);
				}*/
				
				//numbers.update(factor);
			/*	changeArray(mult[countZoomIn]);
				countZoomOut = countZoomIn;
				//System.out.println("Zoom out is " + countZoomOut);
				countZoomIn--;
				System.out.println("Zoom In is " + countZoomIn);
				if (countZoomIn < 0) {
					countZoomIn = mult.length -1;
					countZoomOut = countZoomIn;
					System.out.println("Zoom In reseted is " + countZoomIn);
					for(int i = 0 ; i < mult.length; i++) {
						mult[i] /= 10;
					}
				}*/
				/*if(countMult > 1) {
					countMult--;
					System.out.println("countmult is dicreased ahead of time is : " + countMult);
				}*/
				//System.out.println("multiple is" + mult[countMult]);
			//	countMult--;
			
			/*	if (countMult < 0) {
					countMult = mult.length -1;
					for(int i = 0 ; i < mult.length; i++) {
						mult[i] /= 10;
					}
				}*/
			//	System.out.println("multCount zooming in is" + countMult);
				//changeArray(mult[countMult]);
			//}
	
		}
		
		
		//zooming out wheelRotation is positive
		else if(e.getWheelRotation() > 0) {
			
			
			//int l =(int) lerp(norm,min,max);
			l = (int) lerp(norm,min,max);
			grid.update(l, mouse.getMouseX(), mouse.getMouseY());
			norm -= 0.01;
			
			if(norm < 0) {
				norm = 0.5;
			}
			
			//--------------------------------------------------------------------------------------------------------------
		/*	cross.setMouse();
			cross.update(mouse.getMouseX(), mouse.getMouseY());
			cross.isZoomingOut = true;
			cross.zoomOut();
			factor -= e.getWheelRotation() * 2;
			mouse.reloadMouseLocation();
			grid.update(factor, mouse.getMouseX(), mouse.getMouseY());
			metrics.update(factor, mouse.getMouseX(),mouse.getMouseY());
			numbers.update(factor,mouse.getMouseX(),mouse.getMouseY());
			
			if (factor < 30) {
				factor = 111;
				numbers.incrementCurrentIndex();
				if(!numbers.isDouble) {
					
					numbers.setIntCoordinates(numbers.coordinateIdentities[numbers.getCurrentIndex()]);
				}
				if(numbers.isDouble) {
					
					numbers.setDoubleCoordinates(numbers.decimalIdentities[numbers.getCurrentIndex()]);
				}*/
				//----------------------------------------------------------------------------------------------------------
				//System.out.println("multiple is" + mult[countMult]);
				//System.out.println(countZoomOut);
				//changeArray(mult[countZoomOut]);
			//	countZoomIn = countZoomOut;
				//System.out.println("Zoom In is " + countZoomIn);
			//	countZoomOut++;
			//	System.out.println("Zoom out is " + countZoomOut);
			//	countMult++;
				//System.out.println("multCount zooming out is" + countMult);
				
			//	System.out.println("array length is " + mult.length);
				//System.out.println("multCount is" + countMult);
			/*	if(countZoomOut == mult.length) {
					countZoomOut = 0;
					countZoomIn = 0;
					System.out.println("Zoom out reseted is " + countZoomOut);
					for(int i = 0 ; i < mult.length; i++) {
						mult[i] *= 10; 
					}
				}*/
				
				//--------------------------------------------------------------------------------------------------------------
		//}
			
		}
		//System.out.println(factor);
		//System.out.println(numberTo10[0]);
		//System.out.println(numberRangeDouble[0]);
		//revalidate();
		System.out.println("lerp is: " +l);
		repaint();
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int d = (int)e.getPoint().distance(mouseLoc);
		
		Point c = e.getPoint();
		
		
		
		System.out.println(e.getPoint());
	/*	if(e.getPoint().getX() > mouseLoc.getX()) {
			countX++;
			
			repaint();
		}
		if(e.getPoint().getY() > mouseLoc.getY()) {
			countY++;
			grid.update(factor,countX,countY);
			repaint();
		}
		if(e.getPoint().getX() < mouseLoc.getX()) {
			countX--;
		}
		if(e.getPoint().getY() < mouseLoc.getY()) {
			countY--;
		}*/
		//System.out.println("countY is: " + countY + " countX is: " + countX);
		/*
			countX++;
			countY++;
			
		*/
		
		/*mouse.reloadMouseLocation();
		centerX = mouse.getMouseX();
		centerY=  mouse.getMouseY();
		grid.update(factor,centerX,centerY);
		metrics.update(factor,centerX , centerY);
		numbers.update(factor, centerX, centerY);
		cross.update(centerX, centerY);
		pivot.update(centerX,centerY);
		
		repaint();*/
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseLoc = e.getPoint();
	/*	Point mouseLocM = e.getPoint();
		System.out.println(mouseLocM.getX()+" , " + mouseLocM.getY());*/
	}
}
