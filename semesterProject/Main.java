package semesterProject;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);// creates new scanner object
		System.out.println("Welcome!"); 
		System.out.println("Which job would you like to do? (1 or  2): ");
		int chooseJob = scanner.nextInt();
		
		if (chooseJob == 1) {
			//sends choice to server?
		} // closes if 1
		else if (chooseJob == 2) {
			//sends choice to server?
		} // closes if 2
		else {
			System.out.println("\nERROR! this is an invalid choice.");
			System.out.println("\nRe-enter your choice below: ");
			System.out.println("choose job 1 or 2: ");
			chooseJob = scanner.nextInt();
			}
		scanner.close();
	}// closes main method
	
}// closes class
