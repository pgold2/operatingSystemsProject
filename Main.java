// package semesterProject;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);// creates new scanner object

		
		System.out.println("Which kind of conversion would you like to do?");
		System.out.println("1. DISTANCE (km/mi) \n2. WEIGHT (lb/kilo");
		int chooseConversion = scanner.nextInt();
		
		if (chooseConversion == 1) {
			distanceOption(scanner);
		} // closes if 1
		else if (chooseConversion == 2) {
			weightOption(scanner);
		} // closes if 2
		else {
			System.out.println("\nERROR! this is an invalid choice.");
			System.out.println("\nRe-enter your choice below: ");
			System.out.println("1. convert to miles \n2. convert to km");
			chooseConversion = scanner.nextInt();
			}
	}// closes main method
		
	public static void distanceOption(Scanner scanner) {
 	System.out.println("Please enter the total distance in miles/km: ");
		double distance = scanner.nextDouble();// stores input as distance

		System.out.println("Enter your choice below: ");
		System.out.println("1. convert to miles \n2. convert to km");
		int choice = scanner.nextInt();// stores input as choice
		// calls method that sends choice to appropriate classes and methods
		distanceMenu(scanner, distance, choice);
	}//closes distance Option method 

	// method: validates "choice" and sends "choice" to appropriate classes and methods
	public static void distanceMenu(Scanner scanner, double distance, int choice) {
		if (choice == 1) {
			// calls class.method that converts from km to miles
			// class.convertToMiles(distance);
			System.out.println(" you picked 1"); // this is here just for testing and clarification purposes
		} // closes if 1
		else if (choice == 2) {
			// calls class.method that converts from miles to km
			// class.convertToKm(distance);
			System.out.println(" you picked 2"); // this is here just for testing and clarification purposes
		} // closes if 2
		else {
			System.out.println("\nERROR! this is an invalid choice.");
			System.out.println("\nRe-enter your choice below: ");
			System.out.println("1. convert to miles \n2. convert to km");
			choice = scanner.nextInt();
			distanceMenu(scanner, distance, choice);
		}

	}// closes menu method
	
	public static void weightOption(Scanner scanner) {
	 	System.out.println("Please enter the total weight in lbs/kilo: ");
			double weight = scanner.nextDouble();// stores input as distance

			System.out.println("Enter your choice below: ");
			System.out.println("1. convert to kilo \n2. convert to lbs");
			int choice = scanner.nextInt();// stores input as choice
			// calls method that sends choice to appropriate classes and methods
			weightMenu(scanner, weight, choice);
		}//closes distance Option method 

	// method: validates "choice" and sends "choice" to appropriate classes and methods
		public static void weightMenu(Scanner scanner, double weight, int choice) {
			if (choice == 1) {
				WeightConverter weightConverter = new WeightConverter(100.00, 1);
				double convertedWeight = weightConverter.chooseConversion();
				System.out.println(" you picked 1"); // this is here just for testing and clarification purposes
			} // closes if 1
			else if (choice == 2) {

				WeightConverter weightConverter = new WeightConverter(45.3, 2);
				double convertedWeight = weightConverter.chooseConversion();
				System.out.println(" you picked 2"); // this is here just for testing and clarification purposes
			} // closes if 2
			else {
				System.out.println("\nERROR! this is an invalid choice.");
				System.out.println("\nRe-enter your choice below: ");
				System.out.println("1. convert to kilo \n2. convert to lbs");
				choice = scanner.nextInt();
				weightMenu(scanner, weight, choice);
			}

		}// closes menu method
}// closes class
