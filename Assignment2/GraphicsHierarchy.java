/*
 * File: GraphicsHierarchy.java
 * Name: 
 * Section Leader: 
 * ----------------------------
 * This file is the starter file for the GraphicsHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class GraphicsHierarchy extends GraphicsProgram {
	
	private static final int BoxWidth = 100;
	private static final int BoxHeight = 40;

	/*
	 * Method Name : run
	 * Description : Draws a box above and the four boxes below,
	 *               and connects them with lines.*/
	public void run() {
		drawTopBox();
		drawBottomBoxes();
		drawLines();
	}

	/*
	 * Method Name : drawTopBox
	 * Description : Sets the x- and y- coordinates of the top box
	 *               and draw the box with the text "GObject" at that location.*/	
	private void drawTopBox() {
		double x, y;
		
		x = 0.5 * getWidth() - 0.5 * BoxWidth;
		y = getHeight() / 6.0;
		drawBoxWithLabel(x, y, "GObject");
	}
	
	/*
	 * Method Name : drawBoxWithLabel
	 * Input param : (double)x, (double)y, (String)text
	 * Description : Draws a box on the x- and y- coordinates with the text centered in the box.
	 *               The position of the text is determined by the width and height of the text, 
	 *               the coordinates, width and height of the box.*/
	private void drawBoxWithLabel(double x, double y, String text) {
		double labX, labY;
		
		GLabel lab = new GLabel(text);
		labX = x + 0.5 * BoxWidth - 0.5 * lab.getWidth();
		labY = y + 0.5 * BoxHeight + 0.5 * lab.getAscent();
		lab.setLocation(labX, labY);
		GRect rec = new GRect(x, y, BoxWidth, BoxHeight);
		add(rec);
		add(lab);
	}
	
	/*
	 * Method Name : drawBottomBoxes
	 * Description : Draws four boxes, extending the startX by a certain length.
	 * On the center of boxes, writes "GLabel", "GLine", "GOval", "GRect" respectively
	 * depending on the index of the box.*/
	private void drawBottomBoxes() {
		double startX, startY;
		String[] labs = {"GLabel", "GLine", "GOval", "GRect"};
		
		startX = 0.5 * (getWidth() - (4 + 3 / 2.0) * BoxWidth);
		startY = (5 / 6.0) * getHeight() - BoxHeight;
		for (int idx = 0; idx < 4; idx++) {
			drawBoxWithLabel(startX, startY, labs[idx]);
			startX += (1 + 1 / 2.0) * BoxWidth;
		}
	}
	
	/*
	 * Method Name : drawLines
	 * Description : After calculating the start coordinates(TopX, TopY) from the top box,
	 * and arrival coordinates(BottomX, BottomY) of the first box below, 
	 * connect start and arrival coordinates moving arrival coordinates to the next box.*/ 
	private void drawLines() {
		double TopX, TopY, BottomX, BottomY;
		
		TopX = 0.5 * getWidth();
		TopY = getHeight() / 6.0 + BoxHeight;
		BottomX = 0.5 * (getWidth() - (4 + 3 / 2.0) * BoxWidth) + 0.5 * BoxWidth;
		BottomY =  (5 / 6.0) * getHeight() - BoxHeight;
		for (int idx = 0; idx < 4; idx++) {
			GLine line = new GLine(TopX, TopY, BottomX, BottomY);
			add(line);
			BottomX += (1 + 1 / 2.0) * BoxWidth;
		}
	}
}