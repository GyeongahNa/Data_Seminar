/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	/* Method Name : run
	 * Description : Draw check board patterns by passing through each streets.*/
	public void run() {
		fillOneStreet();
		changeDirToNextStreet();
		while (frontIsClear()) {
			moveToNextStreet(); 
			fillOneStreet();
			changeDirToNextStreet(); 
		}
	}
	
	/* Method Name : fillOneStreet
	 * Description : After filling in the first corner, 
	 *               fill in the remaining corners checking what is filled in the previous(Behind) corner.*/
	private void fillOneStreet() {
		fillFirstCorner();
		while (frontIsClear()) {
			move();
			checkBehindAndFill();
		}
	}

	/* Method Name : fillFirstCorner
	 * Description : If the street is an odd number of lines,
	 *               check the state of the corner on the right side of the first corner and fill it.
	 *               If the street is an even number of lines,
	 *               check the state of the corner on the left side of the first corner and fill it.*/
	private void fillFirstCorner() {
		if (facingEast()) //Odd number line
			checkRightAndFill();
		else if (facingWest()) //Even number line
			checkLeftAndFill();
	}

	/* Method Name : checkRightAndFill
	 * Description : Place a beeper on the corner
	 *               when there is a wall or when the beeper is not placed on the right side of the corner. 
	 *               In the opposite case, nothing is left in the corner.*/
	private void checkRightAndFill() {
		if (rightIsBlocked())
			putBeeper();
		else {
			turnRight();
			move();
			turnAround();
			if (!beepersPresent()) { move(); putBeeper();}
			else move();
			turnRight();
		}
	}

	/* Method Name : checkLeftAndFill
	 * Description : Place a beeper on the corner
	 *               when the beeper is not placed on the left side of the corner.
	 *               Otherwise, nothing is left in the corner.*/
	private void checkLeftAndFill() {
		turnLeft();
		move();
		turnAround();
		if (!beepersPresent()) { move(); putBeeper();}
		else move();
		turnLeft();
	}

	/* Method Name : checkBehindAndFill
	 * Description : Place the beeper on the corner 
	 *               when the beeper is not placed behind the corner.
	 *               In other case, nothing is left in the current corner.*/
	private void checkBehindAndFill() {
		turnAround();
		move();
		turnAround();
		if (!beepersPresent()) { move(); putBeeper();}
		else move();
	}

	/* Method Name : changeDirToNextStreet
	 * Description : If the street has an odd number of lines, change direction to the left,
	 *               if the street has an even number of lines, change direction to the right.*/
	private void changeDirToNextStreet() {
		if (facingEast()) //Odd number line
			turnLeft();
		else if (facingWest()) //Even number line
			turnRight();
	}
	
	/* Method Name : moveToNextStreet
	 * Description : Go forward once and changes direction.
	 *               If the street has an odd number of lines, changes direction to the left,
	 *               if the street has an even number of lines, changes direction to the right.*/
	private void moveToNextStreet() {
		move();
		if (rightIsBlocked()) //Odd number line
			turnLeft();
		else if (leftIsBlocked()) //Even number line 
			turnRight();
	}
}