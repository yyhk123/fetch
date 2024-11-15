package com.example.demo.api.model;
/**
 * The Item includes details about the item's description and price.
 * 
 * Example:
 *   {
 *     "shortDescription": "Gatorade",
 *     "price": "2.25"
 *   }
 *}
 */
public class Item {
	/*
	 * Brief description of the item
	 * */
	private String shortDescription;
	
	/*
	 * price of the each items
	 * */
    private double price;
    
    public Item(String shortDescription, double price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
