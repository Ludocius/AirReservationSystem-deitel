/**
 * 
 */
package com.exercises.aircompany;

/**
 * @author Lucius Fernatore
 * @version .0.1
 * {@summary}
 * Created validation Input system
 * Created automation for seeding seats
 * Created validation for classes' seats assignments
 * Created check for all seats assigned
 */

import java.util.Scanner;
import java.util.*;
import java.security.SecureRandom;

public class AirReservationSystem {

	//Fields or instance variables
	private static Scanner scanner = new Scanner(System.in);
	private static int input;
	private final static int QUIT = -1;
	private final static int FIRST_CLASS = 1;
	private final static int SECOND_CLASS = 2;
	private static boolean isValidInput;
	private static boolean[] seatsAvailability = {false, false, false, false, false, false, false, false, false, false};
	private static SecureRandom numberRandom = new SecureRandom();
	private static int counterSeatsFirstClass = 0;
	private static int counterSeatsEconomicalClass = 0;
	private static int firstClassSeatsLenght = seatsAvailability.length % 2 == 0 ? seatsAvailability.length / 2 : (seatsAvailability.length / 2) + 1; 
	private static int constraintTries; 
	private static boolean isFullInFirstClass = false;
	private static boolean isFullInEconomicalClass = false;

	
	//Getters and Setters
	public static boolean getIsFullInEconomicalClass()
	{
		return isFullInEconomicalClass;
	}
	
	public static void setIsFullInEconomicalClass(boolean isFullInEconomicalClass)
	{
		AirReservationSystem.isFullInEconomicalClass = isFullInEconomicalClass;
	}
	
	public static boolean getIsFullInFirstClass()
	{
		return isFullInFirstClass;
	}
	
	public static void setIsFullInFirstClass(boolean isFullInFirstClass)
	{
		AirReservationSystem.isFullInFirstClass = isFullInFirstClass;
	}
	
	public static int getConstraintTries()
	{
		return constraintTries;
	}
	
	public static void setConstraintTries(int constraintTries)
	{
		AirReservationSystem.constraintTries = (constraintTries == 0 ? AirReservationSystem.constraintTries = 0: AirReservationSystem.constraintTries++);
	}
	
	public static int getFirstClassSeatsLenght()
	{
		return firstClassSeatsLenght;
	}
	
	public static void setSeatsAvailability(int index, boolean isAvailable)
	{
		AirReservationSystem.seatsAvailability[index] = isAvailable;
	}
	
	public static boolean[] getSeatsAvailability()
	{
		return seatsAvailability;
	}
	
	public static int getCounterSeatsEconomicalClass()
	{
		return counterSeatsEconomicalClass;
	}
	
	public static void setCounterSeatsEconomicalClass()
	{
		AirReservationSystem.counterSeatsEconomicalClass++;
	}
	
	public static int getCounterSeatsFirstClass()
	{
		return counterSeatsFirstClass;
	}
	
	public static void setCounterSeatsFirstClass()
	{
		AirReservationSystem.counterSeatsFirstClass++;
	}
	
	public static void setInputValidation(boolean isValidInput)
	{
		AirReservationSystem.isValidInput = isValidInput;
	}
	
	public static boolean getInputValidation()
	{
		return isValidInput;
	}
	
	public static void setInput(int input)
	{
		AirReservationSystem.input = input;
	}
	
	public static int getInput()
	{
		return input;
	}
	
	//Methods
	public static void main(String[] args) {
		startCycle();
	}
	
	//TODO Create a check when seats are all full
	
	public static void checkSeatsFull()
	{
		if(getIsFullInEconomicalClass() && getIsFullInFirstClass())
		{
			System.out.println("All seats are full");
			setInput(QUIT);
		}
	}
	
	public static void giveAnotherOption(int classSelected)
	{
		System.out.println("WARNING!"
				+ "\nThe seats of this class are all assigned"
				+ "\nDo you like to pick the other class?"
				+ "\nPress Yes(y/Y) or not(n/N):");
		String character = scanner.next();
		if(character.equals("Y") || character.equals("y"))
		{
			setInput(classSelected);
		}
		else if(character.equals("N") || character.equals("n"))
		{
			System.out.println("You can wait three hours until the next fly, please wait!"
					+ "	Come back soon!");
			setInput(-1);
		}
		else
		{
			System.out.println("value entered no valid, please enter a valid value!");
			giveAnotherOption(classSelected);
		}
	}
	
	public static void verifySeatAssignmentsFirstClass(int start, int end)
	{
		if(getCounterSeatsFirstClass() == end) {
			setIsFullInFirstClass(true);
			giveAnotherOption(SECOND_CLASS);
		}
		else
		{
			assignSeats(start, end);
		}
	}

	public static void verifySeatAssignmentsEconomicalClass(int start, int end)
	{

		System.out.println("Counter Two value: " + getCounterSeatsEconomicalClass());
		if(getCounterSeatsEconomicalClass() == end)
		{
			setIsFullInEconomicalClass(true);
			giveAnotherOption(FIRST_CLASS);
		}
		else {
			assignSeats(start, end);
		}
	}
	
	public static void inputInformation()
	{
		System.out.printf("%16s%n%15s%n%17s%n%9s%n%10s "
						,"Enter one of the following options: "
						,"1.First class"
						,"2.Economy class"
						,"-1.Exits"
						,"YOUR INPUT:");
		setInput(scanner.nextInt());
	}
	
	public static void startCycle()
	{
		do {
			inputInformation();
			validateInput();
			checkSeatsFull();
		}while(getInput() != QUIT);
	}
	
	public static String setMessage()
	{
		String message = getInputValidation() ? "Entered Value approved.": "Entered value invalid. Please, enter a valid";
		return message;
	}
	
	public static void validateInput()
	{
		if(getInput() == FIRST_CLASS && !(getIsFullInFirstClass()))
		{
			setInputValidation(true);
			System.out.printf("%n%s%n%s%n",setMessage(),"You chose the first class option and your seats is: .");
			verifySeatAssignmentsFirstClass(0, firstClassSeatsLenght);
			setCounterSeatsFirstClass();
		}
		else if(getInput() == SECOND_CLASS && !(getIsFullInEconomicalClass()))
		{
			setInputValidation(true);
			System.out.printf("%n%s%n%s%n",setMessage(),"You chose the economy class option.");
			verifySeatAssignmentsEconomicalClass(firstClassSeatsLenght, firstClassSeatsLenght);
			setCounterSeatsEconomicalClass();
		}
		else if(getInput() == QUIT)
		{
			setInputValidation(true);
			System.out.printf("%n%s%n%s%n",setMessage(), "You chose to quit the app.");
		}
		else
		{	
			setInputValidation(false);
			System.out.printf("%n%s%n",setMessage());
		}
	}
	
	public static void assignSeats(int start, int end)
	{
		int getRandomNumber = start + numberRandom.nextInt(end);
		int limit = end;
		if(!getSeatsAvailability()[getRandomNumber] && getConstraintTries() < limit)
		{
			setSeatsAvailability(getRandomNumber, true);
			System.out.println("Seat number: " + getRandomNumber + " assigned!");
		}
		else
		{
			assignSeats(start, end);
			setConstraintTries(1);
		}
	}
}
