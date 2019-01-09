import java.text.DecimalFormat;

/* 
 * File name: OnlineStore2.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 10 November, 2018
 * 
 */ 

//This is a Book which inherits from Item. This Book is a type of item in the store.

public class Book extends Item
{
	private String author;
	private int isbn;
	
	//Default Constructor
	public Book()
	{
		super();
	}
	
	//Constructor sets the properties based on the user's input
	public Book(String name, double cost, String author, int isbn)
	{
		super(name,cost); //Calls the Item Class's constructor to set the title and price of the book
		
		//Setting the Book's unique properties
		this.author = author;
		this.isbn = isbn;
	}
	
	///getAuthor, getPrice, and getIsbn are getters
	public String getAuthor()
	{
		return this.author;
	}
	
	public int getIsbn()
	{
		return this.isbn;
	}
	
	public Double getPrice()
	{
		return (super.getPrice() - (super.getPrice() * .9));
	}
	
	
	//toString overridden to display the book's information.
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return "Title: " + this.getTitle() + "|" + "Author: " + this.getAuthor() + "|" + "Price: " + df.format(this.getPrice())
		+ "|" + "ISBN: " + this.getIsbn();
	}
}
