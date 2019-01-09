import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Scanner;


/* 
 * File name: OnlineStoreDriver.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 7 December, 2018
 * 
 */

//This is the online store driver that runs the store.
//This program allows the user to manage inventory and/or shop for items in the store.

public class OnlineStoreDriver {
	
	public static void main(String[] args) 
	{
		char userMainMenuChoice; //Holds the user's input for the main menu
		boolean repeatMainMenu; //Checks to see if the menu should be displayed again
		boolean isLoggedIn = false; //Checks if the user has logged on
		ArrayList<Book> bookList = new ArrayList<Book>(); //List of books and audio books
		ArrayList<DVD> dvdList = new ArrayList<DVD>(); //List of dvds
		ArrayList<Book> shoppingCartBooks = new ArrayList<Book>();
		ArrayList<DVD> shoppingCartDVDs = new ArrayList<DVD>();
		ArrayList<Item> shoppingCart = new ArrayList<Item>();
		
		//This loop displays the main menu until the user decides to exit
		//This loop also carries out the user's request from the menu by calling the method attached their choice
		
		do
		{
			userMainMenuChoice = displayMenu(); //Shows the menu and takes the user's input
			
			//Switch statement takes the user's input and calls a method depending on the user's input.
			switch(userMainMenuChoice)
			{
			case 'A': //Display login screen for store managers
				isLoggedIn = managerLogin(isLoggedIn, bookList, dvdList);
				if(!isLoggedIn)
				{
					System.out.println("Unrecognized Credentials");
				}
				break;
			case 'B': //Display customer menu
				customerCatalog(bookList,dvdList,shoppingCart,shoppingCartBooks,shoppingCartDVDs);
				break;
			case 'C': //Exit loop to end the program
				break;
			default:
				System.out.println("This option is not acceptable");
				break;
			}
			
			//If/else checks to see if the main menu should be brought up again
			if(userMainMenuChoice != 'C')
			repeatMainMenu = true;
			else
			repeatMainMenu = false;
			
		} while (repeatMainMenu);

	}
	
	
	/************************************************************************************************
	 * Methods within the driver that are outside of main.
	 * 
	 ************************************************************************************************/
	/// This method displays a menu that asks for the user's input and returns the user's input
		public static char displayMenu()
		{
			//Input object used to get user's input and menuChoice stores their input.
			Scanner input = new Scanner(System.in);
			String menuChoice;
			char choiceValue = 'f';
			
			//These outputs display the main menu
			System.out.println("**Welcome to the Comets Books and DVDs Store**");
			System.out.println(" ");
			System.out.println("Please select your role:");
			System.out.println("A - store manager");
			System.out.println("B - customer");
			System.out.println("C - exit store");
			
			//Taking the user's input
			try
			{
				menuChoice = input.nextLine();
				if(menuChoice.length() == 1)
				{
					menuChoice =menuChoice.toUpperCase();
					choiceValue = menuChoice.charAt(0);
				}
			}
			catch(java.util.InputMismatchException e) //If user enters in anything other than a char
			{
				return 'f';
			}
			return choiceValue;
		}

		/*
		 * MANAGER CATALOG METHODS
		 * 
		 */
//*********************************************************************************************************************	
	///Displays the books and dvds in the catalog
		public static void displayCatalog(ArrayList<Book> bookList, ArrayList<DVD> dvdList)
				{
					Collections.sort(bookList);
					Collections.sort(dvdList);
					
					//ListIterator objects to print each item in their respective ArrayList
					ListIterator<DVD> dvdIterator = dvdList.listIterator();
					ListIterator<Book> bookIterator = bookList.listIterator();
					
					//Prints all of the books
					while(bookIterator.hasNext())
					{
						System.out.println(bookIterator.next());
					}
					
					//Separates the books and dvds visually on screen
					System.out.println("-----------------------------------------------------------------------------------------------------------------");
					
					//Prints all of the dvds
					while(dvdIterator.hasNext())
					{
						System.out.println(dvdIterator.next());
					}
				
					System.out.println(" "); //Just to add space between the bottom of the catalog and the menu intro
				}
		
//*********************************************************************************************************************			
		///Login for managers. After logging on, open up the manager catalog.
		public static boolean managerLogin(boolean isLoggedIn, ArrayList<Book> bookList, ArrayList<DVD> dvdList)
		{
			if(!isLoggedIn)
			{
				Validator isValid = new Validator(); //Checks if the strings are empty
				
				Scanner input = new Scanner(System.in);
				String username = ""; //Holds username input
				String password = ""; //Holds password input
				boolean isInputValid = false;
				FileIO read = new FileIO();
				
				while(!isInputValid)
				{
					System.out.println("Please enter your username: xxxxx");
					username = input.nextLine();
					isInputValid = isValid.isNonEmptyString(username);
				}
				
				isInputValid = false;
				
				while(!isInputValid)
				{
					System.out.println("Please enter your password: yyyyy");
					password = input.nextLine();
					isInputValid = isValid.isNonEmptyString(password);
				}
				
				isLoggedIn = read.readCredentials(username, password);
				if(isLoggedIn)
				{
					managerCatalog(bookList,dvdList);
				}
				
				return isLoggedIn;
			}
			else
			{
				managerCatalog(bookList, dvdList);
				isLoggedIn = true;
				return isLoggedIn;
			}
			
			
		}

//*********************************************************************************************************************			
		///Manager catalog menu
		public static void managerCatalog(ArrayList<Book> bookList, ArrayList<DVD> dvdList)
		{
			boolean repeatMenu;
			FileIO fileIO = new FileIO();
			int userChoice;
			do
			{
				userChoice = displayManagerMenu();
				//Switch statement takes the user's input and calls a method depending on the user's input.
				switch(userChoice)
				{
				case 1: //Add Book to the Book ArrayList
					if(AddBook(bookList, false)) //Print success
					{
						System.out.println("Book successfully added.");
					}
					else //Print Failed addition
					{
						System.out.println("Book has not been added.");
					}
					
					break;
				case 2: //Add AudioBook to the Book ArrayList
					if(AddBook(bookList, true)) //Print success
					{
						System.out.println("Book successfully added.");
					}
					else //Print failed addition
					{
						System.out.println("Book has not been added.");
					}
					break;
				case 3: //Add DVD to the DVD ArrayList
					if(AddDvd(dvdList)) //Print success
					{
						System.out.println("DVD successfully added.");
					}
					else //Print failed addition
					{
						System.out.println("DVD has not been added.");
					}
					break;
				case 4: //Remove Book/AudioBook from the Book ArrayList
					if(RemoveBook(bookList))
					{
						System.out.println("Deletion Successful");
						displayCatalog(bookList,dvdList); //Display the catalog after a successful deletion
					}
					else //Print failed deletion
					{
						System.out.println("The Book doesn't exist in the catalog. Deletion unsuccessful");
					}
					break;
				case 5: //Remove DVD from the DVD ArrayList
					if(RemoveDvd(dvdList))
					{
						System.out.println("Deletion Successful");
						displayCatalog(bookList,dvdList); //Display the catalog after a successful deletion
					}
					else //Print failed deletion
					{
						System.out.println("The DVD doesn't exist in the catalog. Deletion unsuccessful");
					}
					break;
				case 6: //Display the catalog
					displayCatalog(bookList,dvdList);
					break;
				case 7:
					fileIO.writeBackup(bookList, dvdList);
					break;
				case 9: //Exit menu
					break;
				
				
				default:
					System.out.println("This option is not acceptable");
					break;
				}
				
				//If/else checks to see if the main menu should be brought up again
				if(userChoice != 9)
				repeatMenu = true;
				else
				repeatMenu = false;
				
			} while(repeatMenu);
			
			
			
		}
//*********************************************************************************************************************			
		///This method adds a book to the catalog
		public static boolean AddBook(ArrayList<Book> bookList, boolean isAudio)
		{
					
			Scanner input = new Scanner(System.in); //Object made to take user input
			boolean isInputValid = false; //Used for validating input
			//Following holds the user's input on the book information
			int isbnInput = 0;
			String titleInput = "Default";
			String authorInput = "Default";
			double priceInput = 0;
			Validator isValid = new Validator();

			//This loop validates the user's input. If the input is invalid, the user gets to try again.
			while(!isInputValid)
			{
				System.out.println("Please enter the book's ISBN number");
				//Checking user's input
				try
				{
					isbnInput = input.nextInt(); //Take user input
					input.nextLine(); //Eats the newline character leftover from taking integer input.						
					isInputValid = isValid.isPositiveInput(isbnInput); //Check if input is valid at this point
							
					for(int i = 0; i < bookList.size(); i++)
					{
						if(bookList.get(i).getIsbn() == isbnInput)
						{
							System.out.println("This book already exists in the catalog.");
							return false;
						}
					}
				}
				catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
				{
					System.out.println("Invalid input, try again");
					input.next();
				}

			}
					
			isInputValid = false; //Reset validation check
					
			//This loop validates the user's input. If the input is invalid, the user gets to try again.
			while(!isInputValid)
			{
				System.out.println("Please enter the book's title");
				//Checking user's input
				try
				{
					titleInput = input.nextLine(); //taking user input				
					isInputValid = isValid.isNonEmptyString(titleInput); //Check if input is valid
				}
				catch(java.util.InputMismatchException e) //If user enters in a type mismatch for the string, catch
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
			}
					
			isInputValid = false; //Reset validation check
					
			//This loop validates the user's input. If the input is invalid, the user gets to try again.
			while(!isInputValid)
			{
				System.out.println("Please enter the book's price");
				//Checking user's input
				try
				{
					priceInput = input.nextDouble(); //Taking user input
					input.nextLine(); //Eats the newline character leftover	
					isInputValid = isValid.isPositiveInput(priceInput); //Check if input is valid at this point
				}
				catch(java.util.InputMismatchException e) //If user enters in anything other than a double
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
			}
					
			isInputValid = false; //Reset validation check

			//This loop validates the user's input. If the input is invalid, the user gets to try again.
			while(!isInputValid)
			{
				System.out.println("Please enter the book's author");
				//Checking user's input
				try
				{
					authorInput = input.nextLine(); //taking user input
					isInputValid = isValid.isNonEmptyString(authorInput); //Check if input is valid at this point
				}
				catch(java.util.InputMismatchException e) //If user enters in anything that has type mismatch
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
			}
					
			if(!isAudio) //Checks if the book will be an audio book.
			bookList.add(new Book(titleInput, priceInput, authorInput, isbnInput)); //Adding the book to the catalog
			else //Make audio book if book is audio book
			{
				double runningTimeInput = 0; //Run Time default, but will be filled with user input
				isInputValid = false; // Reset input validation
						
			//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
				{
					System.out.println("Please enter the book's runningTime number");
					//Checking user's input
					try
					{
					runningTimeInput = input.nextDouble();
					isInputValid = isValid.isPositiveInput(runningTimeInput); //Check if input is valid at this point
				}
				catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
			}						
			//Add audio book
			bookList.add(new AudioBook(titleInput, priceInput, isbnInput, authorInput, runningTimeInput));
		}
				
		return true; //Successful Addition
				
		}
		
//*********************************************************************************************************************			
		///This method adds a dvd to the catalog
		public static boolean AddDvd(ArrayList<DVD> dvdList)
				{
					Scanner input = new Scanner(System.in); //Object made to take user input
					boolean isInputValid = false; //Used for repeating validation
					Validator isValid = new Validator(); //Validates input
					//Following holds the user's input on the dvd information
					int dvdCodeInput = 0;
					int yearInput = 0;
					String titleInput = "Default";
					String directorInput = "Default";
					double priceInput = 0;
					
				
				//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
					{
						System.out.println("Please enter the DVD code");
						//Checking user's input
						try
						{
							dvdCodeInput = input.nextInt(); //Taking user input
							input.nextLine(); //Eats the newline character leftover
														
							for(int i = 0; i < dvdList.size(); i++) //Looks for duplicate dvd codes
							{
								if(dvdList.get(i).getDvdCode() == dvdCodeInput)
								{
									System.out.println("This DVD already exists in the catalog.");
									return false;
								}
							}
							
							isInputValid = isValid.isPositiveInput(dvdCodeInput) ; //Check if input is valid at this point
						}
						catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
						{
							System.out.println("Invalid input, try again");
							input.next();
						}		
					}
				
				isInputValid = false; //Reset validation check
				
				//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
				{
					System.out.println("Please enter the DVD's title");
					//Checking user's input
					try
					{
						titleInput = input.nextLine(); //taking user input
						isInputValid = true; //Input is valid at this point
					}
					catch(java.util.InputMismatchException e) //If user enters in a type mismatch for the string, catch
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
				}
				
				isInputValid = false; //Reset validation check
				
				//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
				{
					System.out.println("Please enter the director's name");
					//Checking user's input
					try
					{
						directorInput = input.nextLine(); //taking user input
						isInputValid = true; //Input is valid at this point
					}
					catch(java.util.InputMismatchException e) //If user enters in a type mismatch for the string, catch
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
				}
				
				isInputValid = false; //Reset validation check
				
				//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
				{
					System.out.println("Please enter the DVD's price");
					//Checking user's input
					try
					{
						priceInput = input.nextDouble(); //Taking user input
						input.nextLine(); //Eats the newline character leftover
						
						if(priceInput < 0) //Checks if user enters negative price
						{
							throw new IllegalArgumentException("No negative numbers");
						}
						
						isInputValid = isValid.isPositiveInput(priceInput) ; //Check if input is valid at this point
					}
					catch(java.util.InputMismatchException e) //If user enters in anything other than a double
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
					catch(IllegalArgumentException e)
					{
						System.out.println(e.getMessage());
					}
				}
				
				isInputValid = false; //Reset validation check
				
				//This loop validates the user's input. If the input is invalid, the user gets to try again.
				while(!isInputValid)
				{
					System.out.println("Please enter the Year the DVD was made");
					//Checking user's input
					try
					{
						yearInput = input.nextInt(); //Taking user input
						input.nextLine(); //Eats the newline character leftover
						isInputValid = isValid.isPositiveInput(yearInput) ; //Check if input is valid at this point
					}
					catch(java.util.InputMismatchException e) //If user enters in anything other than a double
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
				}
				
				//Add new DVD
				dvdList.add(new DVD(titleInput,priceInput,yearInput,dvdCodeInput, directorInput));
				
				return true; //Successful Addition
				}
//*********************************************************************************************************************	
		//Removes a book from the book list
		public static boolean RemoveBook(ArrayList<Book> bookList)
				{
					boolean isDeleted = false; //Checks for successful deletion to return
					int isbnInput; // User's input in which book will be deleted
					int bookToDelete = -1; //Holds the index of the book to delete
					Scanner input = new Scanner(System.in); //Takes user input
					
					//Prompt user
					System.out.println("Please enter the book's ISBN number");
					
					//Input validation
					try
					{
						isbnInput = input.nextInt();
						
						//If there's no mismatch, look for the book and save it's index
						for(int i = 0; i < bookList.size(); i++)
						{
							if(bookList.get(i).getIsbn() == isbnInput)
							{
								isDeleted = true; //Deletion will be successful if the book is found
								bookToDelete = i; //Now holding index of the book to delete
							}
						}
					}
					catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
					
					//Final validation for deleting the book.
					if(isDeleted && bookToDelete > -1)
					{
						bookList.remove(bookToDelete);
					}
					
					return isDeleted;
				}
				
//*********************************************************************************************************************	
		///Removes a DVD from the dvd list
		public static boolean RemoveDvd(ArrayList<DVD> dvdList)
				{
					boolean isDeleted = false;
					int dvdCodeInput;
					int dvdToDelete = -1;
					Scanner input = new Scanner(System.in);
					
					System.out.println("Please enter the DVD Code");
					
					try
					{
						dvdCodeInput = input.nextInt();
						
						for(int i = 0; i < dvdList.size(); i++)
						{
							if(dvdList.get(i).getDvdCode() == dvdCodeInput)
							{
								isDeleted = true;
								dvdToDelete = i;
							}
						}
					}
					catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
					{
						System.out.println("Invalid input, try again");
						input.next();
					}
					
					if(isDeleted && dvdToDelete > -1)
					{
						dvdList.remove(dvdToDelete);
					}
					
					return isDeleted;
				}
//*********************************************************************************************************************	
		///Visual representation of the manager's menu. Displays the menu as text onto the screen.
		public static int displayManagerMenu()
		{
			//Input object used to get user's input and menuChoice stores their input.
			Scanner input = new Scanner(System.in);
			int menuChoice;
			
			//These outputs display the main menu
			System.out.println("**Welcome to the Comets Books and DVDs Store (Catalog Section)**");
			System.out.println(" ");
			System.out.println("Choose from the following options");
			System.out.println("1 - Add Book");
			System.out.println("2 - Add AudioBook");
			System.out.println("3 - Add DVD");
			System.out.println("4 - Remove Book");
			System.out.println("5 - Remove DVD");
			System.out.println("6 - Display Catalog");
			System.out.println("7 - Create backup file");
			System.out.println("9 - Exit Catalog Section");
		
			
			//Taking the user's integer input
			try
			{
				menuChoice = input.nextInt();
			}
			catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
			{
				return -999;
			}
			return menuChoice;
		}
		
		/*
		 * CUSTOMER CATALOG METHODS
		 * 
		 */

		/// This method displays a menu that asks for the user's input and returns the user's input
		public static int displayCustomerMenu()
		{
			//Input object used to get user's input and menuChoice stores their input.
			Scanner input = new Scanner(System.in);
			int menuChoice;
			
			//These outputs display the main menu
			System.out.println("**Welcome to the Comets Books and DVDs Store**");
			System.out.println(" ");
			System.out.println("Choose from the following options");
			System.out.println("1 - Browse books inventory (price low to high)");
			System.out.println("2 - Browse DVDs inventory (price low to high)");
			System.out.println("3 - Add a book to the cart");
			System.out.println("4 - Add a DVD to the cart");
			System.out.println("5 - Delete a book from cart");
			System.out.println("6 - Delete a DVD from cart");
			System.out.println("7 - View cart");
			System.out.println("8 - Checkout");
			System.out.println("9 - Exit Store");
			
			//Taking the user's integer input
			try
			{
				menuChoice = input.nextInt();
			}
			catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
			{
				return -999;
			}
			return menuChoice;
			
		}
//*********************************************************************************************************************
		///This function calls other functions based on the user's input and repeats until the user chooses to exit.
		public static void customerCatalog(ArrayList<Book> bookList, ArrayList<DVD> dvdList, ArrayList<Item> shoppingCart, ArrayList<Book> bookCart, ArrayList<DVD> dvdCart)
		{
			boolean repeatMenu; //Check if the menu should repeat
			int userChoice; //Holds user's menu choice
			//Keeps the lists sorted Collections.sort(...)
			Collections.sort(bookList); 
			Collections.sort(dvdList);
			
			do
			{
				userChoice = displayCustomerMenu(); //Take user choice from the menu
				
				switch(userChoice)
				{
				case 1: //Browse book inventory
						displayBooks(bookList);
					break;
				case 2: //Browse DVD inventory
						displayDVDs(dvdList);
					break;
				case 3: //Add a book to the cart
						if(addBookToCart(bookList,bookCart))
						{
							System.out.println("Addition to the cart sucessful");
						}
						else
						{
							System.out.println("Addition to the cart unsucessful");
						}
					break;
				case 4: //Add a DVD to the cart
						if(addDVDToCart(dvdList,dvdCart))
						{
							System.out.println("Addition to the cart sucessful");
						}
						else
						{
							System.out.println("Addition to the cart unsucessful");
						}
					break;
				case 5: //Delete a book from cart
						displayBooks(bookCart);
						if(removeBook(bookCart))
						{
							System.out.println("Removal from the cart sucessful");
						}
						else
						{
							System.out.println("Removal from the cart unsucessful");
						}
					break;
				case 6: //Delete a DVD from cart
						displayDVDs(dvdCart);
						if(removeDVD(dvdCart))
						{
							System.out.println("Removal from the cart sucessful");
						}
						else
						{
							System.out.println("Removal from the cart unsucessful");
						}
					break;
				case 7: //View cart
						displayCart(dvdCart, bookCart, shoppingCart);
					break;
				case 8: // Checkout
						displayCart(dvdCart, bookCart, shoppingCart);
						clearCart(shoppingCart, bookCart, dvdCart);

					break;
				case 9: //Exit menu
					break;
				default:
					System.out.println("This option is not acceptable");
					break;
				}
				
				//If/else checks to see if the main menu should be brought up again
				if(userChoice != 9)
				repeatMenu = true;
				else
				repeatMenu = false;
				
			}while (repeatMenu);
		}
//*********************************************************************************************************************
		///This function adds books to the cart
		public static boolean addBookToCart(ArrayList<Book> bookList, ArrayList<Book> shoppingCart)
		{
			Scanner input = new Scanner(System.in);
			int userChoice = 0;
			boolean isValidChoice = false;
			Validator isValid = new Validator();
			System.out.println("Select a product from the inventory (EX: 1 for the first item)");
			
			try
			{
				userChoice = input.nextInt();
				isValidChoice = isValid.isPositiveInput(userChoice);
				isValidChoice = isValid.isOutOfBounds(bookList, userChoice);

			}
			catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
			{
				System.out.println("Invalid input, try again");
				input.next();
			}
			catch(java.lang.ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Invalid input, try again");
				input.next();
			}
			if(isValidChoice)
			{
				shoppingCart.add(bookList.get(userChoice-1));
			}
				return isValidChoice;
		}
//*********************************************************************************************************************
		///This function adds items to the cart
		public static boolean addDVDToCart(ArrayList<DVD> dvdList, ArrayList<DVD> shoppingCart)
		{
			Scanner input = new Scanner(System.in);
			int userChoice = 0;
			boolean isValidChoice = false;
			Validator isValid = new Validator();
			System.out.println("Select a product from the inventory (EX: 1 for the first item)");
			
			try
			{
				userChoice = input.nextInt();
				isValidChoice = isValid.isPositiveInput(userChoice);
				isValidChoice = isValid.isOutOfBounds(dvdList, userChoice);

			}
			catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
			{
				System.out.println("Invalid input, try again");
				input.next();
			}
			catch(java.lang.ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Invalid input, try again");
				input.next();
			}
			if(isValidChoice)
			{
				shoppingCart.add(dvdList.get(userChoice-1));
			}
				return isValidChoice;
		}
//*********************************************************************************************************************
		///This function displays the books in the catalog
		public static void displayBooks(ArrayList<Book> bookList)
		{	
			//Sort list
			Collections.sort(bookList);
			
			//ListIterator objects to print each item in their respective ArrayList
			ListIterator<Book> bookIterator = bookList.listIterator();
			
			if(bookList.isEmpty())
			{
				System.out.println("Sorry, there are no books.");
			}
			
			//Prints all of the books
			while(bookIterator.hasNext())
			{
				System.out.println(bookIterator.next());
				
			}
			
			System.out.println(" "); //Just to add space between the bottom of the catalog and the menu intro
		}
//*********************************************************************************************************************
		///This function displays the books in the catalog
		public static void displayDVDs(ArrayList<DVD> dvdList)
		{
			//Sort list
			Collections.sort(dvdList);
			
			//ListIterator objects to print each item in their respective ArrayList
			ListIterator<DVD> dvdIterator = dvdList.listIterator();
			
			if(dvdList.isEmpty())
			{
				System.out.println("Sorry, there are no DVDs.");
			}
			
			//Prints all of the dvds
			while(dvdIterator.hasNext())
			{
				System.out.println(dvdIterator.next());
			}
			
			System.out.println(" "); //Just to add space between the bottom of the catalog and the menu intro
		}
//*********************************************************************************************************************
		///This function displays the cart
		public static void displayCart(ArrayList<DVD> dvdList, ArrayList<Book> bookList, ArrayList<Item> shoppingCart)
		{
			//Reset the cart shopping cart
			shoppingCart.clear();
			double total = 0; //Running total
			
			DecimalFormat df = new DecimalFormat("#.##"); //Formatting
			
			//Add books to general cart
			for(int i = 0; i < bookList.size(); i++)
			{
				shoppingCart.add(bookList.get(i));
			}
			
			//Add dvds to general cart
			for(int i = 0; i < dvdList.size(); i++)
			{
				shoppingCart.add(dvdList.get(i));
			}
			
			//Sort cart
			if(!shoppingCart.isEmpty())
			Collections.sort(shoppingCart);
			
			if(shoppingCart.isEmpty())
			{
				System.out.println("Your cart is empty.");
			}
			else
			{
			//Print cart
			for(int i = 0; i < shoppingCart.size(); i++)
			{
				System.out.format("|%-20s|", shoppingCart.get(i).getTitle());
				System.out.format("|%-20s|", df.format(shoppingCart.get(i).getPrice()));
				System.out.printf("%n", ' ');
				System.out.println("------------------------------------------------------------------");
				total= shoppingCart.get(i).getPrice() + total;
			}
			
			
			System.out.format("|%-20s|", "Total + Tax");
			System.out.format("|%-20s|", (df.format(calculateTotal(total))));
			System.out.printf("%n", ' ');
			}
		}
//*********************************************************************************************************************
		///This function removes a book
		public static boolean removeBook(ArrayList<Book> bookCart)
		{
			boolean isRemoved = false;
			Scanner input = new Scanner(System.in);
			int userChoice = 0;
			boolean isValidChoice = false;
			Validator isValid = new Validator();
			
			if(bookCart.isEmpty())
			{
				return isRemoved;
			}
			else
			{
				System.out.println("Select a book from your cart to remove");
				try
				{
					userChoice = input.nextInt();
					isValidChoice = isValid.isPositiveInput(userChoice);
					isValidChoice = isValid.isOutOfBounds(bookCart, userChoice);

				}
				catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
				if(isValidChoice)
				{
					bookCart.remove(userChoice-1);
					isRemoved = true;
				}
			}
			return isRemoved;
		}
//*********************************************************************************************************************
		///This function removes a dvd
		public static boolean removeDVD(ArrayList<DVD> dvdCart)
		{
			boolean isRemoved = false;
			Scanner input = new Scanner(System.in);
			int userChoice = 0;
			boolean isValidChoice = false;
			Validator isValid = new Validator();

			if(dvdCart.isEmpty())
			{
				return isRemoved;
			}
			else
			{
				System.out.println("Select a dvd from your cart to remove");
				try
				{
					userChoice = input.nextInt();
					isValidChoice = isValid.isPositiveInput(userChoice);
					isValidChoice = isValid.isOutOfBounds(dvdCart, userChoice);

				}
				catch(java.util.InputMismatchException e) //If user enters in anything other than an integer
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e)
				{
					System.out.println("Invalid input, try again");
					input.next();
				}
				if(isValidChoice)
				{
					dvdCart.remove(userChoice-1);
					isRemoved = true;
				}
			}
			return isRemoved;
		}
//*********************************************************************************************************************
		///This function clears the cart
		public static void clearCart(ArrayList<Item> list, ArrayList<Book> bookCart, ArrayList<DVD> dvdCart)
		{
			list.clear();
			bookCart.clear();
			dvdCart.clear();
		}
//*********************************************************************************************************************
		public static double calculateTotal(double total)
		{
			double tax = .0825; //The value of the tax
			return total+=(total*tax);
		}
}

