/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	private int number, count;

	/*
	 * Method Name : run
	 * Description : Prints prompt, receives an integer from a user, 
	 *               calculates Hailstone sequence and counts the number of times it took to reach 1.
	 *               Finally, prints the value of count.*/
	public void run() {
		printPrompt();
		if (!setNumber())
			return ;
		setCount();
		printCount();
	}

	/*
	 * Method Name : printPrompt
	 * Description : Prints prompt.*/
	private void printPrompt() {
		println("This program computes Hailstone sequences.");
	}

	/*
	 * Method Name : setNumber
	 * Return value: (boolean)true / (boolean)false
	 * Description : Sets number by receiving an integer from a user.
	 *               If the number is negative or zero, prints Error messages and returns false.
	 *               Otherwise, return true.*/
	private boolean setNumber() {
		number = readInt("Enter a number: ");
		if (number <= 0) {
			println("Error: The number should be positive integer.");
			return (false);
		} else return (true);
	}
	
	/*
	 * Method Name : setCount
	 * Description : Calculating Hailstone sequence 
	 *               and sets count as the number of times it took to reach 1.
	 *               When the number is even, next value is obtained by dividing it by 2,
	 *               when the number is odd, next value is calculated 
	 *               by multiplying it by three and add one.*/
	private void setCount() {
		count = 0;
		while (number != 1) {
			print(number);
			if (number % 2 == 0) {
				number = number / 2;
				println(" is even, so I take half = " + number);
			} else {
				number = 3 * number + 1;
				println(" is odd, so I make 3n + 1 = " + number);
			}
			count++;
		}
	}

	/*
	 * Method Name : printCount
	 * Description : Prints the value of count.*/	
	private void printCount() {
		println("The process took " + count + " steps to reach 1.");
	}
}