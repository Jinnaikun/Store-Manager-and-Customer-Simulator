import java.text.DecimalFormat;

/* 
 * File name: OnlineStore2.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 10 November, 2018
 * 
 */ 

//This is the AudioBook class that inherits from the Book class.

public class AudioBook extends Book
{
	private double runningTime; //Running Time is unique to audio books.
	
	//Default constructor calls it's parent's constructor
	public AudioBook() 
	{
		super();
	}
	
	//Constructor called when the user puts in the required information
	public AudioBook(String name, double cost, int isbn, String author, double runningTime) 
	{
		super(name, cost, author, isbn); //Uses the Book Class's constructor to set properties similar to books
		this.runningTime = runningTime; //Setting it's own unique properties.
	}
	
	///getPrice and getRunningTime are getters
	public Double getPrice()
	{
		return (super.getPrice() - (super.getPrice() * .5));
	}
	
	public double getRunningTime()
	{
		return this.runningTime;
	}

	//toString overridden to display the audio book's information.
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return "Title: " + this.getTitle() + "|" + "Author: " + this.getAuthor() + "|" + "Price: " + df.format(this.getPrice())
		+ "|" + "ISBN: " + this.getIsbn() + "|" + "RunningTime: " + this.getRunningTime();
	}
}
