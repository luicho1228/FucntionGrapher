package linearalgebra_impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

public class CoordinateSystem {

	String currentNumber;
	Font font;
	int gap;
	Color color;
	Color background;
	int centerX;
	int centerY;
	int spaceFactorX;
	int spaceFactorY;
	int factor;
	int[]num;
	double[] doubleNumArray;
	DecimalFormat decimalFormat;
	boolean isDouble;
	
	String digitFormat;
	int baseCount = 1;
	int[] coordinatesIntegers;
	double[] coordinatesDecimals;
	int previousIndex;
	int nextIndex;
	int currentIndex;
	int [] coordinateIdentities;
	double [] decimalIdentities;
	
	
	public CoordinateSystem(int numberInt, double numberDouble, int centerX, int centerY, int factor) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.spaceFactorX = 0;
		this.spaceFactorY = 0;
		gap = 30;
		digitFormat = "#0.0";
		decimalFormat = new DecimalFormat(digitFormat);
		
		//num = numArray;
		//this.doubleNumArray = doubleNumArray;
		font = new Font(Font.SANS_SERIF, Font.BOLD,12);
		this.factor = factor;
		//setCurrentArray();
		
		coordinatesIntegers = new int[10];
		coordinatesDecimals = new double[10];
		coordinateIdentities = new int[] {1,2,5,10};
		decimalIdentities = new double[] {0.1, 0.2, 0.5};
		loadDefaultIndexes();
		setIntCoordinates(coordinateIdentities[currentIndex]);
		setDoubleCoordinates(decimalIdentities[currentIndex]);
		/*for(int i = 0; i < coordinatesDecimals.length; i++) {
			System.out.println(coordinatesDecimals[i]);
		}*/
		
		/*
		loadDefaultValues();*/
		
	}
	
	
	public void update(int factor, int mouseX, int mouseY) {
		this.factor = factor;
		this.centerX = mouseX;
		this.centerY = mouseY;
		setIntCoordinates(coordinateIdentities[currentIndex]);
	}
	
	
	

	
	
/*	public void loadIdentity() {
		for(int i = 0; i <  coordinatesIntegers.length; i++) {
			
		}
	}*/
	
	
	//---------------------------Base Management----------------------------
	
	public void increaseBaseDouble() {
		for(int i =0; i < decimalIdentities.length; i++) {
			decimalIdentities[i] /= 0.1;
		}
	}
	
	public void decreaseBaseDouble() {
		for(int i =0; i < decimalIdentities.length; i++) {
			decimalIdentities[i] *= 0.1;
			
		}
	}
	
	public void increaseBase() {
		for(int i = 0 ; i < coordinateIdentities.length; i++) {
			coordinateIdentities[i] *= 10; 
		}
	}
	
	
	
	public void decreaseBase() {
		for(int i = 0 ; i < coordinateIdentities.length; i++) {
			coordinateIdentities[i] /= 10;
		}
	}
	
	public void changeBase() {
		if(coordinateIdentities[nextIndex] > coordinateIdentities.length) {
			loadDefaultIndexes();
			increaseBase();
		}
		if(coordinateIdentities[previousIndex] < -1) {
			currentIndex = coordinateIdentities.length - 1;
			nextIndex = currentIndex + 1;
			previousIndex = currentIndex - 1;
			decreaseBase();
		}
		
	}
	
	//-------------------------------Coordinates and identities Management----------------------------------------
	
	public void setDoubleCoordinates(double identity) {
		double countDouble = identity;
		for(int i = 0; i < 10; i++) {
			coordinatesDecimals[i] = countDouble;
			countDouble += identity;
		}
	}
	
	public void setIntCoordinates(int identity) {
		int countIdentity = identity;
		for(int i = 0; i < 10; i++) {
			
			coordinatesIntegers[i] = countIdentity;
			countIdentity += identity;
		}
	}
	
	public int getCoordinateIdentity() {
		return coordinateIdentities[currentIndex];
	}
	
	public int getNextCoordinateIdentity() {
		return coordinateIdentities[nextIndex];
	}
	
	public int getPreviousCoordinateIdentity() {
		return coordinateIdentities[previousIndex];
	}
	
	public void loadDefaultValues() {
		int defaultValuesInts = 1;
		for (int i = 0; i < coordinatesIntegers.length; i++) {
			 coordinatesIntegers[i] = defaultValuesInts;
			 defaultValuesInts++;
		}
		double defaultValuesDecimal = 0.1;	
		for(int i = 0; i < 10; i++) {
			coordinatesDecimals[i] = defaultValuesDecimal;
			defaultValuesDecimal += 0.1;
		}
	}
	
	//-------------------------Index Management------------------------------
	
	
	public void loadDefaultIndexes() {
		currentIndex = 0;
		nextIndex = currentIndex + 1;
		previousIndex = currentIndex - 1;
	}
	
	
	public int getCurrentIndex() {
		return currentIndex;
	}
	public int getNextIndex() {
		return nextIndex;
	}
	
	public int getPreviousIndex() {
		return previousIndex;
	}
	
	public void decreaseCurrentIndex() {
		currentIndex--;
		nextIndex--;
		previousIndex--;
		baseCount--;
		if(baseCount == 0 && currentIndex < 0) {
			currentIndex = decimalIdentities.length-1;
			nextIndex = currentIndex + 1;
			previousIndex = currentIndex - 1;
			baseCount = -1;
			setNumbersToDoubles();
		}
		else if (currentIndex < 0 && baseCount != 0) {
			currentIndex = decimalIdentities.length-1;
			nextIndex = currentIndex + 1;
			previousIndex = currentIndex - 1;
			if(!isDouble) {
				/*currentIndex = coordinateIdentities.length-2;
				nextIndex = currentIndex + 1;
				previousIndex = currentIndex - 1;*/
				decreaseBase();
			}
			if (isDouble) {
				
				this.decimalFormat = new DecimalFormat(digitFormat + 0);
				decreaseBaseDouble();
				/*for(int i = 0; i < coordinatesDecimals.length; i++) {
				System.out.println(coordinatesDecimals[i]);
				}*/
					
			}
		}
		
	}
	
	public void incrementCurrentIndex() {
		currentIndex++;
		nextIndex++;
		previousIndex++;
		baseCount++;
		
		if(baseCount == 0 && currentIndex == 3) {
			currentIndex = 0;
			nextIndex = currentIndex + 1;
			previousIndex = currentIndex - 1;
			baseCount = 1;
			setNumbersToIntegers();
		}
		
		else if(baseCount != 0 && currentIndex == 3) {
			currentIndex = 0;
			nextIndex = currentIndex + 1;
			previousIndex = currentIndex - 1;
			if(!isDouble) {
				increaseBase();
				
			}
			
			else if(isDouble) {
				increaseBaseDouble();
				}
			}
	}
	
	
	//----------------------------------------------------------------------------
	
	public void setNumbersToDoubles() {
		isDouble = true;
	}
	
	public void setNumbersToIntegers() {
		isDouble = false;
	}
	
	public void setCurrentArray() {
	/*	if (numberInt == 0) {
			currentNumber = Double.toString(numberDouble);
		}
		else if(numberDouble == 0) {
			currentNumber = Integer.toString(numberInt);
		}
		else {
			currentNumber = Integer.toString(numberInt);
		}*/
	}
	
	public void draw(Graphics2D g2d) {
		
		
		int spaceFactorX = this.spaceFactorX;
		int spaceFactorY = this.spaceFactorY;
		int centerX = this.centerX;
		int centerY = this.centerY;
		int gap = this.gap;
		int[] coordinatesIntegers = this.coordinatesIntegers;
		double[] coordinatesDecimals = this.coordinatesDecimals;
		DecimalFormat decimalFormat = this.decimalFormat;
		int factor = this.factor;
		FontMetrics fm = g2d.getFontMetrics();
		int stringW =0;
		int stringH = 0;
		int stringWidthNeg = 0;
		int stringHeigthNeg = 0;
		int stringwDecimal = 0;
		int stringhDecimal =0;
		int stringwNegDecimal =0;
		int stringhNegDecimal = 0;
		
		
		g2d.setColor(Color.gray);
		g2d.setFont(font);
		for(int i = 0; i < coordinatesIntegers.length; i++) {
			spaceFactorX += factor;
			spaceFactorY += factor;
			
			
			if(!isDouble) {
				Rectangle2D stringBounds = fm.getStringBounds(Integer.toString(coordinatesIntegers[i]), g2d);
				Rectangle2D stringBoundsNegative = fm.getStringBounds(Integer.toString(coordinatesIntegers[i] * -1), g2d);
				stringW = (int)stringBounds.getWidth() + 2;
				stringH = (int)stringBounds.getHeight();
				stringWidthNeg =(int) stringBoundsNegative.getWidth() + 4;
				stringHeigthNeg =(int) stringBoundsNegative.getHeight();
				g2d.setColor(Color.WHITE);
				g2d.fillRect( centerX - gap - 2, (int)(centerY - spaceFactorY - stringH + (stringH/2) + 3 ), stringW,stringH);
				g2d.fillRect( centerX - gap - 2 ,(centerY + spaceFactorY - stringHeigthNeg + (stringHeigthNeg/2)+ 3 ), stringWidthNeg, stringHeigthNeg);
				g2d.fillRect( centerX + spaceFactorX - 2  - (stringW/2), centerY + gap - stringH + 2 , stringW,stringH);
				g2d.fillRect( centerX - spaceFactorX - 2  - (stringWidthNeg/2), centerY + gap -  stringHeigthNeg + 2 , stringWidthNeg, stringHeigthNeg);
				g2d.setColor(Color.gray);
				g2d.drawString(Integer.toString(coordinatesIntegers[i]), centerX - gap,(centerY - spaceFactorY + (stringH/2)) );
				g2d.drawString(Integer.toString(coordinatesIntegers[i] * -1), centerX - gap,(centerY + spaceFactorY + (stringHeigthNeg/2)));
				g2d.drawString(Integer.toString(coordinatesIntegers[i]), centerX + spaceFactorX  - (stringW/2),centerY + gap);
				g2d.drawString(Integer.toString(coordinatesIntegers[i] * -1), centerX - spaceFactorX - (stringWidthNeg/2) ,centerY + gap);
			}else if (isDouble) {
				
				String formatD = decimalFormat.format(coordinatesDecimals[i]);
				String formatDN = decimalFormat.format(coordinatesDecimals[i] * -1);
				Rectangle2D stringBoundsDecimal = fm.getStringBounds(formatD, g2d);
				Rectangle2D stringBoundsDecimalNegative = fm.getStringBounds(formatDN, g2d);
				stringwDecimal = (int) stringBoundsDecimal.getWidth() + 2;
				stringhDecimal = (int) stringBoundsDecimal.getHeight();
				stringwNegDecimal = (int) stringBoundsDecimalNegative.getWidth() + 4;
				stringhNegDecimal = (int) stringBoundsDecimalNegative.getHeight();
				g2d.setColor(Color.white);
				g2d.fillRect( centerX - gap -2  - (stringwDecimal/2), (int)(centerY - spaceFactorY - stringhDecimal + (stringhDecimal/2) + 3 ), stringwDecimal,stringhDecimal);
				g2d.fillRect( centerX - gap -2 - (stringwNegDecimal/2),(centerY + spaceFactorY - stringhNegDecimal + (stringhNegDecimal/2)+ 3 ), stringwNegDecimal, stringhNegDecimal);
				g2d.fillRect( centerX + spaceFactorX - (stringwDecimal/2), centerY + gap - stringhDecimal + 2 , stringwDecimal,stringhDecimal);
				g2d.fillRect( centerX - spaceFactorX - (stringwNegDecimal/2), centerY + gap -  stringhNegDecimal + 2 , stringwNegDecimal, stringhNegDecimal);
				g2d.setColor(Color.gray);
				g2d.drawString(decimalFormat.format(coordinatesDecimals[i]), centerX - gap - (stringwDecimal/2),(centerY - spaceFactorY + (stringhDecimal/2)) );
				g2d.drawString(decimalFormat.format(coordinatesDecimals[i] * -1), centerX - gap - (stringwNegDecimal/2),(centerY + spaceFactorY + (stringhNegDecimal/2)));
				g2d.drawString(decimalFormat.format(coordinatesDecimals[i]), centerX + spaceFactorX - (stringwDecimal/2),centerY + gap);
				g2d.drawString(decimalFormat.format(coordinatesDecimals[i] * -1) , centerX - spaceFactorX - (stringwNegDecimal/2),centerY + gap);
			}
		//	g2d.drawLine(centerX - gap, centerY + spaceFactorX, centerX + gap, centerY+spaceFactorX);
			//g2d.drawLine(centerX - gap, centerY - spaceFactorX, centerX + gap, centerY-spaceFactorX);
			
			/*for(int j = 0; j < centerX; j++) {
				//g2d.drawLine(centerX + spaceFactorY, centerY - gap, centerX + spaceFactorY, centerY + gap);
				//g2d.drawLine(centerX - spaceFactorY, centerY - gap, centerX - spaceFactorY, centerY + gap);
				spaceFactorY += factor;
			}*/
		}
		
	/*	g2d.setColor(Color.BLUE);
		for (int j = 0; j < coordinatesIntegers.length;j++) {
			g2d.fillRect( centerX - gap, centerY - spaceFactorY, 20, 20);
			g2d.fillRect( centerX - gap,(centerY + spaceFactorY), 20, 20);
			spaceFactorX += factor;
			spaceFactorY += factor;
		}*/
		
		//g2d.drawString(currentNumber, 0, 0);
	}
	
}
