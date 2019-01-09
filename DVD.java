import java.text.DecimalFormat;

/* 
 * File name: OnlineStore2.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 10 November, 2018
 * 
 */ 

//This is a DVD, which inherits from Item. This DVD is a type of item in the store.

public class DVD extends Item
{
	private String director;
	private int year;
	private int dvdCode;
	
	//Default constructor sets defaults
	public DVD()
	{
		super();
	}
	
	//Constructor sets the values given by the user
	public DVD(String name, double cost, int year, int dvdCode, String director)
	{
		super(name,cost); //Sets the title and price through the Item class constructor
		
		//Setting the class specific properties
		this.year = year;
		this.dvdCode = dvdCode;
		this.director = director;
	}
	
	///getDirector, getYear, and getDvdCode are all getters
	public String getDirector()
	{
		return this.director;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public int getDvdCode()
	{
		return this.dvdCode;
	}
	public Double getPrice()
	{
		return (super.getPrice() - (super.getPrice() * .8));
	}

	//toString overridden to display the DVD's information.
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return "Title: " + this.getTitle() + "|" + "Director: " + this.getDirector() + "|" + "Price: " + df.format(this.getPrice())
		+ "|" + "Year: " + this.getYear() + "|" + "DvdCode: " + this.getDvdCode();
	}




	
}
