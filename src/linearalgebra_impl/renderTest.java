package linearalgebra_impl;

import java.awt.Color;
import java.awt.Graphics2D;

public class renderTest {

	int w;
	int h;
	int countx =0;
	int countY =0;
	public renderTest() {
		w = 5;
		h = 5;
	}
	
	public void draw(Graphics2D g2d) {
		int countx2 = countx;
		int countY2 = countY;
		for (int i = 0; i< 10; i++) {
			g2d.setColor(Color.BLUE);
			g2d.drawRect(countx2, countY2, w, h);
			countx2 += 100;
			countY2 += 100;
		}
	}
}
