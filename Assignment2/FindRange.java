/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import java.util.ArrayList;
import acm.program.*;

public class FindRange extends ConsoleProgram {

	private int smallest, largest;
	private static final int sentinel = 0;
	private ArrayList<Integer> intArr = new ArrayList<Integer>();

	/*
	 * Method Name : run
	 * Description : Prints prompt, receives a list of integers from a user, 
	 *               find the smallest and largest values and prints them.*/
	public void run() {
		printPrompt();
		setIntArr();
		findSmallest();
		findLargest();
		printRange();
	}
	
	/*
	 * Method Name : printPrompt
	 * Description : Prints prompt.*/
	private void printPrompt() {
		println("This program finds the smallest and largest integers int a list." + 
                "Enter values, one per line, using a 0 to signal the end of the list.");
	}
	
	/*
	 * Method Name : setInArr
	 * Description : Receives integers from the user and store them in intArr
	 *               until the user enter the sentinel.*/
	private void setIntArr() {
		int input;

		input = 1;
		while (input != sentinel) {
			input = readInt(" ? ");
			intArr.add(input);
		}
	}
	
	/*
	 * Method Name : findSmallest
	 * Description : Finds the smallest value navigating the intArr array.
	 *               If intArr doesn't have any integers except for the sentinel,
	 *               smallest would be set as the value of sentinel.*/
	private void findSmallest() {
		int idx;
		
		idx = 0;
		smallest = intArr.get(idx);
		while (intArr.get(idx) != sentinel) {
			if (intArr.get(idx) < smallest)
				smallest = intArr.get(idx);
			idx++;
		}
	}
	
	/*
	 * Method Name : findLargest
	 * Description : Finds the largest value navigating the intArr array.
	 *               If intArr doesn't have any integers except for the sentinel,
	 *               largest would be set as the value of sentinel.*/
	private void findLargest() {
		int idx;
		
		idx = 0;
		largest = intArr.get(idx);
		while (intArr.get(idx) != sentinel) {
			if (intArr.get(idx) > largest)
				largest = intArr.get(idx);
			idx++;
		}
	}
	
	/*
	 * Method Name : printRange
	 * Description : Prints the smallest and largest value. 
	 *               If the smallest and largest values are the value of sentinel,
	 *               prints the error message and exits the program.*/
	private void printRange() {
		if ((smallest == sentinel) && (largest == sentinel)) {
			println("Error: No values have been entered.");
			return ;
		}
		println("The smallest value is " + smallest);
		println("The largest value is " + largest);
	}
}