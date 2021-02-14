/*
 * File: Quadratic.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Quadratic problem.
 */

import acm.program.*;

public class Quadratic extends ConsoleProgram {
	
	private double a, b, c;

	/*
	 * Method Name : run
	 * Description : Prints prompt, receives a, b, c. 
	 *               Otherwise, print the solutions.*/
	public void run() {
		printPrompt();
		setCoefficient();
		if (!validityCheck())
			return ;
		printSolutions();
	}
	
	/*
	 * Method Name : printPrompt
	 * Description : Prints the prompt.*/
	private void printPrompt() {
		println("Enter coefficients for the quadratic equation:");
	}
	
	/*
	 * Method Name : setCoefficient
	 * Description : Receives the integer values for a, b, c from a user,
	 *               and sets them as a member variables.*/
	private void setCoefficient() {
		a = readDouble("a: ");
		b = readDouble("b: ");
		c = readDouble("c: ");
	}
	
	/*
	 * Method Name : validityCheck
	 * Return value: (boolean)true / (boolean)false
	 * Description : If the value of 'a' is zero or b^2 - 4ac is under 0,
	 *               prints the error messages and returns false. Otherwise, returns true.*/
	private boolean validityCheck() {
		if (a == 0) {
			println("Error: The value for 'a' shouldn't be zero.");
			return false;
		} else if ((Math.pow(b, 2) - 4 * a * c) < 0) {
			println("Error: Quantity unber the square root sign is negative.");
			return false;
		}
		return true;
	}
	
	/*
	 * Method Name : printSolutions
	 * Description : Calculates the solutions and prints them. */
	private void printSolutions() {
		double ans1, ans2;
	
		ans1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		ans2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
		println("The first solution is " + ans1);
		println("The Second solution is " + ans2);
	}
}
