/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 12;

	/*
	 * Method Name : run
	 * Description : Going up from base line to the upper line, 
	 *               finds the x and y coordinates where a line of boxes starts,
	 *               and draws each row of boxes.*/
	public void run() {
		int rowCnt;
		double startX, startY;

		rowCnt = BRICKS_IN_BASE;
		while (rowCnt >= 1) {
			startX = getStartX(rowCnt);
			startY = getStartY(rowCnt);
			drawOneRow(rowCnt, startX, startY);
			rowCnt--;
		}
	}
	
	/*
	 * Method Name : getStartX
	 * Input Param : (int)rowCnt
	 * Return value: (double)startX;
	 * Description : Calculates the x-coordinate where the row starts with the parameter
	 *               which means how many boxes should be listed on the line.*/
	private double getStartX(int rowCnt) {
		double centerX, distToLeft, startX;
		
		centerX = getWidth() / 2;
		distToLeft = (rowCnt / 2) * BRICK_WIDTH;
		distToLeft += (rowCnt % 2 == 0) ? 0 : (BRICK_WIDTH / 2.0);
		startX = centerX - distToLeft;
		return (startX);
	}
	
	/*
	 * Method Name : getStartY
	 * Input Param : (int)rowCnt
	 * Return value: (double)startY
	 * Description : Calculates the y-coordinate where the row starts with the parameter
	 *               which means how many boxes should be listed on the line.*/
	private double getStartY(int rowCnt) {
		double startY;
		
		startY = getHeight() - ((BRICKS_IN_BASE - rowCnt + 1) * BRICK_HEIGHT);
		return (startY);
	}
	
	/*
	 * Method Name : drawOneRow
	 * Input Param : (int)rowCnt, (double)startX, (double)startY
	 * Description : Draws the rowCnt of boxes in the row
	 *               increasing the x-coordinate(startX) by BRICK_WIDTH.*/
	private void drawOneRow(int rowCnt, double startX, double startY) {
		while (rowCnt >= 1) {
			GRect brick = new GRect(startX, startY, BRICK_WIDTH, BRICK_HEIGHT);
			add(brick);
			rowCnt--;
			startX += BRICK_WIDTH;
		}
	}
}