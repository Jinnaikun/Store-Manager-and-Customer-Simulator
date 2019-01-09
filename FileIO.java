import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;
import java.time.LocalDate;

public class FileIO 
{
	private File userCredentials = new File("credentials.txt"); //File containing login credentials
	
	///This method reads the user's input and checks if the input matches the file contents.
	///Returns true if the username and password matches, returns false when they don't match.
	public boolean readCredentials(String username, String password)
	{
		String tempUsername; //Holds a username from the file
		String tempPassword; //Holds a password from the file
		String tempString; //Holds the string containing the user and password separated by a comma
		boolean isValid = false; //Checks if login information is valid
		
		try
		{
			System.out.println("Validating login credentials...");
			Scanner read = new Scanner(userCredentials);
			
			while(read.hasNextLine())
			{
				tempString = read.nextLine(); //Read username and password combination
				String[] tokens = tempString.split(","); //Split the username and password, separated by a comma
				
				tempUsername = tokens[0]; //Username token
				tempPassword = tokens[1]; //Password token
				
				if(tempUsername.equals(username)) //Check if the username matches
				{
					if(tempPassword.equals(password)) //Check if the password matches
					{
						isValid = true;
					}
				}
				
			}
		}
		catch (FileNotFoundException fnfx) //File credential file not found
		{
			System.out.println("File: " + fnfx + " was not found.");
		}
		
		return isValid;
		
	}
	
	///This methods creates a backup file of the store catalog
	public void writeBackup(ArrayList<Book> bookList, ArrayList<DVD> dvdList)
	{	
		Calendar cal = Calendar.getInstance(); //Calendar object to get the time and date
		LocalDate localDate = LocalDate.now(); //Another date object
		//Name of the backup file with a time stamp at the end
		String backupFileName = "catalog_backup_" + cal.get(Calendar.YEAR) + "_" + localDate.getMonthValue() +
		"_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) +
		"_" + cal.get(Calendar.SECOND) +".txt";
		System.out.println(backupFileName);
		File backupFile = new File(backupFileName); //Create a new file.
		
		
		try
		{
			PrintWriter pw = new PrintWriter(backupFile);
			//ListIterator objects to print each item in their respective ArrayList
			ListIterator<DVD> dvdIterator = dvdList.listIterator();
			ListIterator<Book> bookIterator = bookList.listIterator();
			
			//Prints all of the books
			while(bookIterator.hasNext())
			{
				pw.println(bookIterator.next());
			}
			
			//Separates the books and dvds visually on screen
			pw.println("-----------------------------------------------------------------------------------------------------------------");
			
			//Prints all of the dvds
			while(dvdIterator.hasNext())
			{
				pw.println(dvdIterator.next());
			}
			
			pw.close();
			
		}
		catch (FileNotFoundException fnfx)
		{
			System.out.println("File: " + fnfx + " was not found.");
		}
		
	}

	
}
