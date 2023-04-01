package linearalgebra_impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

public class numbersSys {
	int gap;
	DecimalFormat decimalFormat;
	String digitFormat;
	Font font;
	int screenX,screenY;
	int xy;
	boolean isY = false;
	int number;
	int leadGap;
	public numbersSys(int screenX,int screenY, int xy, int number) {
		this.number = number;
		digitFormat = "#0.0";
		decimalFormat = new DecimalFormat(digitFormat);
		font = new Font(Font.SANS_SERIF, Font.BOLD,12);
		this.screenX= screenX;
		this.screenY = screenY;
		gap = 10;
		this.xy = xy;
		leadGap = 25;
	}
	public void isY() {
		this.isY = true;
	}
		
	public void draw(Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D stringBounds = fm.getStringBounds(Integer.toString(number), g2d);
		
			if(isY) {
				g2d.setColor(Color.white);
				g2d.fillRect(screenX + leadGap, xy - (int)stringBounds.getHeight()+3, (int)stringBounds.getWidth(),(int)stringBounds.getHeight());
				g2d.setColor(Color.black);
				g2d.drawString(Integer.toString(number),screenX + leadGap,xy);
				isY = false;
			}else {
				g2d.setColor(Color.white);
				g2d.fillRect(xy , (screenY + leadGap) - (int)stringBounds.getHeight() + 3, (int)stringBounds.getWidth(),(int)stringBounds.getHeight());
				g2d.setColor(Color.black);
				g2d.drawString(Integer.toString(number), xy, screenY + leadGap);
			}
			number++;
	}
}
