/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/* Method Name : run
	 * Description : From the leftmost and rightmost sides of the first street,
	 *               increase the beeper one by one, and find the center point
	 *               where the beepers from both ends meet. Afterwards, remove all beepers
	 *               except for the center point, and place the Karel at the center position.*/
	public void run() {
		if (frontIsBlocked()) { putBeeper(); return; } //exception handling for 1*1 map
		while (checkLeftNoBeepers()) {
			fillEdge("Left");
			if (checkRightNoBeepers())
				fillEdge("Right"); 
		}
		putBeeper();
		deleteExceptForCenter();
		moveToCenter();
	}

	/* Method Name : checkLeftNoBeepers
	 * Return value: (Boolean) true / (Boolean) false
	 * Description : If there is no beeper on the left side of the current corner, return true. 
	 *               In the opposite case, return false.*/
	private boolean checkLeftNoBeepers() {
		if (BehindIsBlocked()) 	
			return true;
		else if (facingEast()) 	
			return false;
		move();
		if (!beepersPresent())	
			return true;
		else 					
			return false;
	}

	/* Method Name : BehindIsBlocked
	 * Return value: (Boolean) true / (Boolean) false
	 * Description : If there is wall behind the current corner, return true. 
	 *               In the opposite case, return false.*/
	private boolean BehindIsBlocked() {
		turnAround();
		if (frontIsBlocked()) { turnAround(); return true;}
		else { turnAround(); return false;}
	}

	/* Method Name : fillEdge
	 * Parameter   : (String) dir
	 * Description : Place the beeper in the leftmost(rightmost) corner where the beeper is not placed.*/
	private void fillEdge(String dir) {
		moveToWall(dir);
		skipBeepers();
		putBeeper();
	}
	
	/* Method Name : moveToWall
	 * Parameter   : (String) dir
	 * Description : When the input parameter is "Left", move to the left wall. 
	 *               If "Right", move to the right wall.*/
	private void moveToWall(String dir) {
		if (dir == "Left" && facingEast())
			turnAround();
		else if (dir == "Right" && facingWest())
			turnAround();
		while (frontIsClear())
			move();
		turnAround();
	}
	
	/* Method Name : skipBeepers
	 * Description : Pass the continuous beepers and move to the corner where has no beeper.*/
	private void skipBeepers() {
		while (beepersPresent())
			move();
	}
	
	/* Method Name : checkRightNoBeepers
	 * Return value: (Boolean) true / (Boolean) false
	 * Description : If there is no beeper on the right side of the current corner, return true. 
	 *               OtherWise, return false.*/
	private boolean checkRightNoBeepers() {
		move();
		if (!beepersPresent())
			return true;
		else {
			turnAround();
			move();
			turnAround();
			return false;
		}
	}
	
	/* Method Name : deleteExceptForCenter
	 * Description : Remove one beeper at each corner as passing through the street.*/
	private void deleteExceptForCenter() {
		moveToWall("Left");
		while (frontIsClear()) {
			pickBeeper();
			move();
		}
		pickBeeper();
	}
	
	/* Method Name : moveToCenter
	 * Description : Move Karel to the center of the street.*/
	private void moveToCenter() {
		moveToWall("Left");
		while (!beepersPresent())
			move();
	}
}