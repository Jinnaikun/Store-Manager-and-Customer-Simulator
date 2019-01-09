import java.util.ArrayList;
import java.util.Scanner;

public class Validator implements Acceptable {

	
	///This method checks is the string is empty.
	public boolean isNonEmptyString(String s) 
	{
		boolean isInputValid = false; //Used for validating input
		Scanner input = new Scanner(System.in);
		
		//Checking user's input
		try
		{
			System.out.println("Validating input...");
				
			if(s.isEmpty()) //Checks if user enters in an empty title, not including spaces
			{
				throw new IllegalArgumentException("Empty input, please try again.");
			}
				
			isInputValid = true; //Input is valid at this point
		}
		catch(IllegalArgumentException e) //If user enters in an empty title, catch
		{
			System.out.println(e.getMessage());
		}
		
		return isInputValid;
	}

	
	///This method checks if the number is a negative number.
	public boolean isPositiveInput(double d)
	{
		boolean isInputValid = false; //Used for validating input
		Scanner input = new Scanner(System.in);
		
		try
		{
			System.out.println("Validating input...");
			
			if(d < 0) //Checks if user enters negative price
			{
				throw new IllegalArgumentException("No negative numbers");
			}
			
			isInputValid = true; //Input is valid at this point
		}

		catch(IllegalArgumentException e) //User enters a negative number exception
		{
			System.out.println(e.getMessage());
		}

		return isInputValid;
		
	}
	
	///This method checks if the number is a negative number.
	public boolean isPositiveInput(int i)
	{
		boolean isInputValid = false; //Used for validating input
		Scanner input = new Scanner(System.in);
		
		try
		{
			System.out.println("Validating input...");
			
			if(i < 0) //Checks if user enters negative price
			{
				throw new IllegalArgumentException("No negative numbers");
			}
			
			isInputValid = true; //Input is valid at this point
		}

		catch(IllegalArgumentException e) //User enters a negative number exception
		{
			System.out.println(e.getMessage());
		}

		return isInputValid;
	}
	
	public boolean isOutOfBounds(ArrayList itemList, int i)
	{
		boolean isValid = false;
		System.out.println("Validating...");
		
		if(i > itemList.size())
		{
			System.out.println("Invalid choice.");
			return isValid;
		}
		else if(i <= 0)
		{
			System.out.println("Invalid choice.");
			return isValid;
		}
		else
		{
			isValid = true;
			return isValid;
		}
	}

}
