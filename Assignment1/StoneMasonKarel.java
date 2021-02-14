
import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	/* Method Name : run
	 * Description : Fill missing stones of columns which are exactly four units apart.*/
	public void run() {
		while (frontIsClear()) {
			fillOneColumn();
			moveToNextColumn();
		}
		fillOneColumn();
	}
	
	/* Method Name : fillOneColumn
	 * Description : Fill one column with stones and return to the original position.*/	
	private void fillOneColumn() {
		turnLeft();
		moveUpAndFill();
		turnAround();
		moveDown();
		turnLeft();
	}
	
	/* Method Name : moveUpAndFill
	 * Description : Move up to the end of the column filling missing corners with a stone.*/	
	private void moveUpAndFill() {
		while (frontIsClear()) {
			checkAndPutBeeper();
			move();
		}
		checkAndPutBeeper();
	}
	
	/* Method Name : checkAndPutBeeper
	 * Description : Check there're no stones in the current corner and fill in a stone.*/	
	private void checkAndPutBeeper() {
		if (noBeepersPresent())
			putBeeper();
	}
	
	/* Method Name : moveDown
	 * Description : Move down to the end of the column.*/	
	private void moveDown() {
		while (frontIsClear())
			move();
	}
	
	/* Method Name : moveToNextColumn
	 * Description : Go forward four times to move to the next column.*/	
	private void moveToNextColumn() {
		for (int i = 0; i < 4; i++)
			move();
	}
}
