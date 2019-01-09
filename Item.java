/* 
 * File name: OnlineStore2.java
 * Author: Evan Lieu
 * Class: CS2336.503
 * Professor: Vidroha Debroy
 * 10 November, 2018
 * 
 */ 

//Abstract class all store items must inherit.
//All store items must have at least a name and a price to be listed in the store.

public class Item implements Comparable<Item>
{
	private double price;
	private String title;
	
	
	//Default constructor sets item name and price to default values
	public Item()
	{
		this(null,0);
	}
	
	// Constructor sets the title and price of the item
	public Item(String name,double cost)
	{
		this.title = name;
		this.price = cost;
	}
	
	// Returns the price of the item
	public Double getPrice()
	{
		return this.price;
	}
	
	//Returns the name of the item
	public String getTitle()
	{
		return this.title;
	}
	

	//Compares two items in the shop by their price, if they have the same price compare their names.
	public int compareTo(Item o2)
	{
		int result = this.getPrice().compareTo(o2.getPrice());
		
		if(result != 0)
		{
			return result;
		}
		
		if(result == 0)
		{
			result = this.getTitle().compareTo(o2.getTitle());
		}
		
		return result;
	}

}
