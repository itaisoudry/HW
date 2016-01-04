package AverageCalculator;


public class AverageCalculator {

	public static void main(String[] args) {

		int firstNum = Integer.parseInt(args[0]);
		int secondNum = Integer.parseInt(args[1]);
		int thirdNum = Integer.parseInt(args[2]);
		int sum;
		double avg;

		// Calculate sum
		sum = firstNum + secondNum + thirdNum;
		// calculate average
		avg = ((double)sum) / 3;
		// prints sum and average

		System.out.println("The sum is: " + sum + ".");
		System.out.println("The average is: " + avg + ".");

	}

}
