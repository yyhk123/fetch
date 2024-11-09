package com.example.demo.api.model;

import com.example.demo.api.model.Item;
import java.util.*;

public class Receipt {
/**
 * The Receipt includes store purchased from, details items purchased, date and time of the purchase and total amount.
 * 
 * Example:
 * {
 * "retailer": "M&M Corner Market",
 * "purchaseDate": "2022-03-20",
 * "purchaseTime": "14:33",
 * "items": [
 *   {
 *     "shortDescription": "Gatorade",
 *     "price": "2.25"
 *   },{
 *     "shortDescription": "Gatorade",
 *     "price": "2.25"
 *   },{
 *     "shortDescription": "Gatorade",
 *     "price": "2.25"
 *   },{
 *     "shortDescription": "Gatorade",
 *     "price": "2.25"
 *   }
 * ],
 * "total": "9.00"
 *}
 */
	private String retailer;
	private String purchaseDate;
	private String purchaseTime;
	private List<Item> items;
	private double total;
	
	public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, double total) {
		this.retailer = retailer;
		this.purchaseDate = purchaseDate;
		this.purchaseTime = purchaseTime;
		this.items = items;
		this.total = total;
	}
	
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
