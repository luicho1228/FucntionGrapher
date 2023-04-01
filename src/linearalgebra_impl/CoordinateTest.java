package linearalgebra_impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoordinateTest extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, ActionListener,TextListener{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	double offsetX,offsetY;
	int mouseX, mouseY;
	double scaleX,scaleY;
	int startPanX, startPanY;
	int screenX, screenY;
	double worldX, worldY;
	double worldTop = 0, worldLeft 
			= 0, worldRight = 0, worldBottom = 0;
	double gap, innerGap;
	int pivotSX, pivotEX, pivotSY, pivotEY;
	double mouseWorldX_beforeZoom,mouseWorldY_beforeZoom,mouseWorldX_afterZoom,mouseWorldY_afterZoom, sxbefore,sybefore,sxafter,syafter;
	int length;
	numbersSys number;
	double centerX, centerY;
	CustomButton reset;
	int baseNum;
	int[] base;
	int baseIndex;
	int pivotOffsetX, pivotOffsetY;
	double pivotScaleX, pivotScaleY;
	//int pivotOffsetX, pivotOffsetY;
	
	int countWheel = 10;
	double f = 0;
	double t =3.99f;
	NumberFormat format = new DecimalFormat("0.############################################################");
	double ultraGap = 100.0;
	double u = 10.0;
	double r = ultraGap;
	double tempOffsetX, tempOffsetY;
	JTextField text;
	double a = 100;
	double b = 600;
	double c = 0;
	double d = 0;
	
	
	public CoordinateTest(JFrame frame) {
		this.frame = frame;
		offsetX =-frame.getWidth()/2;
		offsetY =-frame.getHeight()/2;
		scaleX = 1.0f;
		scaleY = 1.0f;
		pivotScaleX = 1.0f;
		pivotScaleY = 1.0f;
		pivotOffsetX = -frame.getWidth()/2;
		pivotOffsetY = -frame.getHeight()/2;
		tempOffsetX = offsetX;
		tempOffsetY = offsetY;
		length = 10;
		gap = 0;
		innerGap = 50;
		centerX = -offsetX;
		centerY = -offsetY;
		reset = new CustomButton();
		reset.addActionListener(this);
		base = new int[] {1,2,5,10};
		baseIndex = 0;
		//baseNum = 5;
		
		
		
		
		text = new JTextField();
		text.setPreferredSize(new Dimension(100,100));
		text.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Integer.parseInt(text.getText()) >= 0) {
					d = Integer.parseInt(text.getText());
					repaint();
				}
			}
			
		});
		this.add(text);
		this.setBackground(Color.white);
		this.add(reset);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.setOpaque(true);
		
	}
	
	public void worldToScreen(double worldX, double worldY) {
		screenX = (int) ((worldX - offsetX) * scaleX);
		screenY = (int) ((worldY - offsetY) * scaleY);
	}
	public void screenToWorld(double screenX, int screenY) {
		worldX = (float) ((screenX / scaleX) + offsetX);
		worldY = (float) ((screenY / scaleY) + offsetY);
	}
	public double screenToWorldX(int screenX, double worldX) {
		worldX = (float) ((screenX / scaleX) + offsetX);
		return worldX;
	}
	public double screenToWorldD(int screenX, double worldX) {
		worldX = (double) ((screenX / scaleX) + offsetX);
		return worldX;
	}
	public double screenToWorldY(int screenY, double worldY) {
		worldY = (double) ((screenY / scaleY) + offsetY);
		return worldY;
	}
	public int worldToScreenX( double worldX, int screenX) {
		screenX = (int) ((worldX - offsetX) * scaleX);
		return screenX;
	}
	public int worldToScreenY( double worldY, int screenY) {
		screenY = (int) ((worldY - offsetY) * scaleY);
		return screenY;
	}
	
	public int pivotWorldToScreenX(double worldX, int screenX) {
		screenX = (int) ((worldX - pivotOffsetX) * pivotScaleX);
		return screenX;
	}
	public int pivotWorldToScreenY( double worldY, int screenY) {
		screenY = (int) ((worldY - pivotOffsetY) * pivotScaleY);
		return screenY;
	}
	
	public double pivotScreenToWorldX(int screenX, double worldX) {
		worldX = (screenX / pivotScaleX) + pivotOffsetX;
		return worldX;
	}
	public double pivotScreenToWorldY(int screenY, double worldY) {
		worldY = (screenY / pivotScaleY) + pivotOffsetY;
		return worldY;
	}
	
	public int WorldToScreen(double world) {
		int screen = 0;
		screen = (int)((world - offsetX) * scaleX);
		return screen;
	}
	
	public void increaseBase() {
		for(int i = 0; i < base.length;i++) {
			base[i] = base[i] * 10;
		}
	}
	public void decreaseBase() {
		
		for(int i = 0; i < base.length;i++) {
			base[i] = base[i] / 10;
		}
	}
	
	public double funtion(double x) {
		double y;
		double a = this.a;
		double b = this.b;
		double c = this.c;
		double d = this.d;
		
		y = a * Math.sin(((Math.PI * 2)/ b) * (x + c)) + d;
		
		return y;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);
		g2d.setStroke(new BasicStroke(1));
	
		//-------converting world dimensions to screen dimensions------------
		
		worldLeft = screenToWorldX(0, worldLeft);
		worldRight = screenToWorldX(frame.getWidth(), worldRight);
		worldTop = screenToWorldY(0, worldTop);
		worldBottom = screenToWorldY(frame.getHeight(), worldBottom);
		
		
		
		//-------------------drawing lines---------------------------------
		baseNum = base[baseIndex];
		int linesDrawn = 0;
		int count = baseNum;
		//totalGap = gap + c;
		
		
		g2d.setColor(Color.GRAY);
		for (double x = ultraGap ; x <= worldRight ; x+= ultraGap) {
			if(x <= worldRight && x >=worldLeft) {
				double sx =x;
				double ex =x;
				System.out.println("sx: " + sx);
				int psx = 0, pex = 0;
				psx = worldToScreenX(sx,psx);
				pex =  worldToScreenX(ex,pex);
				g2d.drawLine(psx,0,pex,frame.getHeight());
				//---------------------------------------------------------
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(3));
				worldToScreen(worldX, worldY);
				//screenX = pivotWorldToScreenX(worldX, screenX);
				//screenY = pivotWorldToScreenY(worldY, screenY);
				g2d.drawLine(psx, screenY - length, pex, screenY + length);
				
				number = new numbersSys(screenX, screenY,psx, count);
				number.draw(g2d);
				g2d.setColor(Color.GRAY);
				g2d.setStroke(new BasicStroke(1));
			}
			linesDrawn++;
			count+=baseNum;
		}
		//g2d.setColor(Color.GRAY);
		count = -baseNum;
		for(double y = ultraGap; y <= worldBottom ; y += ultraGap) {
			if(y <= worldBottom && y >= worldTop) {
				double sy = y;
				double ey = y;
				int psy=0,pey=0;
				psy = worldToScreenY(sy,psy);
				pey = worldToScreenY(ey,pey);
				g2d.drawLine(0, psy, frame.getWidth(), pey);
				
				//---------------------------------------------------------
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(3));
				worldToScreen(worldX, worldY);
			//	screenX = pivotWorldToScreenX(worldX, screenX);
				//screenY = pivotWorldToScreenY(worldY, screenY);
				g2d.drawLine(screenX - length,psy , screenX + length, pey);
				number = new numbersSys(screenX, screenY,psy, count);
				number.isY();
				number.draw(g2d);
				g2d.setColor(Color.GRAY);
				g2d.setStroke(new BasicStroke(1));
			}
			linesDrawn++;
			count-=baseNum;
		}
		count = -baseNum;
		for(double x =-ultraGap; x >= worldLeft ; x-= ultraGap) {
			if(x >=worldLeft) {
				double sx =x;
				double ex =x;
				int psx = 0, pex = 0;
				psx = worldToScreenX(sx,psx);
				pex =  worldToScreenX(ex,pex);
				g2d.drawLine(psx,0,pex,frame.getHeight());
				//---------------------------------------------------------
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(3));
				worldToScreen(worldX, worldY);
				//screenX = pivotWorldToScreenX(worldX, screenX);
				//screenY = pivotWorldToScreenY(worldY, screenY);
				g2d.drawLine(psx, screenY - length, pex, screenY + length);
				number = new numbersSys(screenX, screenY,psx , count);
				number.draw(g2d);
				g2d.setColor(Color.GRAY);
				g2d.setStroke(new BasicStroke(1));
				count-=baseNum;
				
			}		
			linesDrawn++;
		}
		count = baseNum;
		for(double y = -ultraGap; y >= worldTop ; y -= ultraGap) {
			if(y >= worldTop) {
				double sy = y;
				double ey = y;
				int psy=0,pey=0;
				psy = worldToScreenY(sy,psy);
				pey = worldToScreenY(ey,pey);
				g2d.drawLine(0, psy, frame.getWidth(), pey);
				
				//-------------------------------------------------
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(3));
				worldToScreen(worldX, worldY);
				//screenX = pivotWorldToScreenX(worldX, screenX);
				//screenY = pivotWorldToScreenY(worldY, screenY);
				g2d.drawLine(screenX - length,psy , screenX + length, pey);
				number = new numbersSys(screenX, screenY,psy, count);
				number.isY();
				number.draw(g2d);
				g2d.setColor(Color.GRAY);
				g2d.setStroke(new BasicStroke(1));
				count+=baseNum;
			}
			linesDrawn++;
		}
		
		
		double worldPerScreenWidthPixel = (worldRight - worldLeft) / frame.getWidth();
		double worldPerScreenHeightPixel = (worldBottom - worldTop) / frame.getHeight();
		int px = 0,py = 0,opx=0,opy=0;
		//opy = worldToScreenX(worldLeft - worldPerScreenWidthPixel, (int) -funtion(worldLeft - worldPerScreenHeightPixel));
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(3));
		for(double x = worldLeft; x < worldRight; x +=  worldPerScreenWidthPixel) {
			double y = -funtion(x);
			px = worldToScreenX(x,px);
			py = worldToScreenY(y,py);
			g2d.drawLine(opx , opy, px, py);
			opx = px;
			opy = py;
		}
		
		//System.out.println("linesdrawn: " + linesDrawn);
		//System.out.println("totalgap: " + totalGap);
		//----------------------------------------------------------------
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.black);
		//-----------------vertical pivot line------------------------------
		
	/*	worldLeft = pivotScreenToWorldX(0, worldLeft);
		worldRight = pivotScreenToWorldX(frame.getWidth(), worldRight);
		worldTop = pivotScreenToWorldY(0, worldTop);
		worldBottom = pivotScreenToWorldY(frame.getHeight(), worldBottom);
		
		*/
		
		pivotSX = worldToScreenX(0, pivotSX);
		pivotSY = worldToScreenY(worldTop, pivotSY);
		pivotEX = worldToScreenX(0, pivotEX);
		pivotEY = worldToScreenY(worldBottom, pivotEY);
		g2d.drawLine(pivotSX, pivotSY, pivotEX, pivotEY);
		//-----------------horizontal pivot line----------------------------
		pivotSX = worldToScreenX(worldLeft, pivotSX);
		pivotSY = worldToScreenY(0, pivotSY);
		pivotEX = worldToScreenX(worldRight, pivotEX);
		pivotEY = worldToScreenY(0, pivotEY);
		g2d.drawLine(pivotSX, pivotSY, pivotEX, pivotEY);
		
		//------------------drawing red circle----------------------------
		
	/*	g2d.setColor(Color.red);
		g2d.fillOval((int) worldLeft,frame.getHeight()/2, 50, 50);
		g2d.setColor(Color.blue);
		g2d.fillOval((int) worldRight,frame.getHeight()/2, 50, 50);
		g2d.setColor(Color.yellow);
		g2d.fillOval(frame.getWidth()/2,(int) worldBottom, 50, 50);
		g2d.setColor(Color.green);
		g2d.fillOval(frame.getWidth()/2,(int) worldTop, 50, 50);
		g2d.setColor(Color.MAGENTA);
		g2d.fillOval((int)(screenToWorldD(0,ultraGap)),(int)(screenToWorldD(0,ultraGap)),100 , 100);*/
		
		//----------------------------center circle------------------------
		
		worldToScreen(worldX, worldY);
		g2d.fillOval(screenX - 4,screenY - 4 , 8, 8);
		
		//System.out.println("screenX" + screenX);
		//g2d.fillOval((int) mouseWorldX_beforeZoom,(int) mouseWorldY_beforeZoom , 50,50);
		
		//System.out.println(scaleX + ", " + scaleY);
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
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
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		double wxbefore = 0,wxafter = 0, wybefore=0,wyafter=0;;
		mouseWorldX_beforeZoom = screenToWorldX(mouseX, mouseWorldX_beforeZoom);
		mouseWorldY_beforeZoom = screenToWorldY(mouseY, mouseWorldY_beforeZoom);
		wxbefore = screenToWorldX(frame.getWidth()/2, wxbefore);
		wybefore = screenToWorldY(frame.getHeight()/2, wybefore);
		//zooming out
		if(e.getWheelRotation() > 0) {
			scaleX *= 0.9f;
			scaleY *=  0.9f;
			if(countWheel > 20) {
				
				
				/*f = worldRight/t;
				System.out.println(f);
				ultraGap += Math.abs(f);
				*/
				
				ultraGap = (double) (1/(scaleX * 2)) * 100;
				
				//ultraGap *= 3.9f;
				countWheel = 10;
				baseIndex++;
				if(baseIndex > base.length -1) {
					baseIndex = 1;
					increaseBase();
				}
			}
			countWheel++;
		}
		//zooming in 
		if(e.getWheelRotation() < 0) {
			scaleX *= 1.1f;
			scaleY *= 1.1f;
			if(countWheel < 0) {
				ultraGap = (double) (1/(scaleX * 2)) * 100;
				countWheel = 10;
				baseIndex--;
				if(baseIndex < 0) {
					baseIndex = base.length-1;
					decreaseBase();
				}	
			}
			countWheel--;
		}
		/*System.out.println("scaleX: " + scaleX);
		System.out.println("scaleY: " + scaleY);
		System.out.println("offsetX: " + offsetX);
		System.out.println("offsetY: " + offsetY);*/
		wxafter = screenToWorldX(frame.getWidth()/2, wxafter);
		wyafter = screenToWorldY(frame.getHeight()/2, wyafter);
		mouseWorldX_afterZoom = screenToWorldX(mouseX,  mouseWorldX_beforeZoom);
		mouseWorldY_afterZoom = screenToWorldY(mouseY, mouseWorldY_beforeZoom);
		offsetX += (mouseWorldX_beforeZoom - mouseWorldX_afterZoom);
		offsetY += (mouseWorldY_beforeZoom - mouseWorldY_afterZoom);
		centerX -= (wxbefore - wxafter);
		centerY -= (wybefore - wyafter);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 10;
				boolean isCenter = false;
				offsetX = tempOffsetX;
				offsetY = tempOffsetY;
				
				while(!isCenter) {
					if(-offsetX <= centerX + 20 && -offsetX > centerX) {
						count = 1;
					}
					if( -offsetX >=  centerX- 20 && -offsetX < centerX) {
						count = 1;
					}
					if(-offsetY >= centerY - 20 && -offsetY <  centerY) {
						count = 1;
					}
					if( -offsetY <= centerY + 20 && -offsetY > centerY) {
						count = 1;
					}
					//--------------------------------------------------------
					if(-offsetX > centerX) {
						offsetX +=  count;
					}
					if(-offsetX < centerX) {
						offsetX -=  count;
					}
					if(-offsetY < centerY) {
						offsetY -=  count;
					}
					if(-offsetY > centerY) {
						offsetY +=  count;
					}
					if(-offsetX == centerX) {
						if(-offsetY == centerY) {
							isCenter = true;
						}
					}
					repaint();
					count = 10;
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();	
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class PairAxis{
	int x;
	int y;
	public PairAxis(int x, int y) {
		this.x = x;
		this.y = y;
	}	
}