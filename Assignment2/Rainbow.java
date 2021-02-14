/*
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Rainbow problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Rainbow extends GraphicsProgram {

	private double startX, startY, radius, interval;

	/*
	 * Method Name : run
	 * Description : Initializes the member variables, 
	 *               paint the background color of canvas and draw rainbows.*/
	public void run() {
		setInitVal();
		getGCanvas().setBackground(Color.cyan);
		drawRainbows();
	}
	
	/*
	 * Method Name : setInitVal
	 * Description : Sets radius(radius of the outermost circle),
	 *               startX, startY(coordinates of the outermost circle)
	 *               and interval(intervals between circles).*/
	private void setInitVal() {
		double winWidth, winHeight, minRadius;
		
		winWidth = getWidth();
		winHeight = getHeight();
		minRadius = Math.sqrt(Math.pow(0.5 * winWidth, 2) + Math.pow(0.5 * winHeight, 2)) + 0.1 * winHeight;
		radius = 1.5 * winHeight - 0.1 * winHeight;
		startX = 0.5 * winWidth - radius;
		startY = 1.5 * winHeight - radius;
		interval = (radius - minRadius) / 7.0;	
	}
	
	/*
	 * Method Name : drawRainbows
	 * Description : Draws and Colors seven circles,
	 *               moving the start coordinates and radius of circle
	 *               by the interval.*/
	private void drawRainbows() {
		for (int idx = 0; idx < 7; idx++) {
			GOval cir = new GOval(startX, startY, 2 * radius, 2 * radius);
			cir.setFilled(true);
			fillColor(cir, idx);
			add(cir);
			startX += interval; 
			startY += interval;
			radius -= interval;
		}
	}
	
	/*
	 * Method Name : fillColor
	 * Input param : (GOval)cir, (int)idx
	 * Description :  Chooses a color according to the idx and color the circle.*/
	private void fillColor(GOval cir, int idx) {
		Color[] colors = {Color.red, Color.orange, Color.yellow, 
                          Color.green, Color.blue, Color.magenta, Color.cyan};
		cir.setColor(colors[idx]);
		cir.setFillColor(colors[idx]);
	}
}