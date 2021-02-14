/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	/* Method Name : run
	 * Description : Move Karel to the newspaper, 
	 *               pick it up and bring the Karel back to its original location.*/
	public void	run() {
		moveToNewspaper();
		getNewsPaper();
		moveToStartPoint();
	}
	
	/* Method Name : moveToNewspaper
	 * Description : Move Karel to the newspaper.*/
	private void moveToNewspaper() {
		moveToEndWall();
		turnRight();
		moveToDoor();
		turnLeft();
	}
	
	/* Method Name : moveToEndWall
	 * Description : Move Karel to the end wall of the street.*/		
	private void moveToEndWall() {
		while (frontIsClear())
			move();
	}
	
	/* Method Name : moveToDoor()
	 * Description : Move Karel to the door.*/
	private void moveToDoor() {
		while (leftIsBlocked())
			move();
	}
	
	/* Method Name : getNewsPaper()
	 * Description : Make Karel go out of the house, pick up the newspaper, and come back inside the house.*/
	private void getNewsPaper() {
		move();
		if (beepersPresent())
			pickBeeper();
		turnAround();
		move();
	}
	
	/* Method Name : moveToStartPoint()
	 * Description : Let Karel return to the starting position.*/
	private void moveToStartPoint() {
		turnRight();
		moveToEndWall();
		turnLeft();
		moveToEndWall();
		turnAround();
	}
}