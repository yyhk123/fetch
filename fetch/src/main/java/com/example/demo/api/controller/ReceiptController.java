package com.example.demo.api.controller;

import java.util.*;
import com.example.demo.api.model.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/receipts")
public class ReceiptController {
	
	private final Map<String, Integer> ids = new HashMap<>();

	/*
	 * Read the receipt and define how many points should be awarded to a receipt
	 * and return id as response.
	 * */
	@PostMapping("/process")
	public Map<String, Object> readReceipt(@RequestBody Receipt receipt) {
		// One point for every alphanumeric character in the retailer name
		final int retailerPoint = readRetailer(receipt.getRetailer());
		
		// 50 points if the total is a round dollar amount with no cents.
		// 25 points if the total is a multiple of 0.25.
		final int roundDollar = readTotal(receipt.getTotal());
		
		// 5 points for every two items on the receipt.
		final int numberOfItems = readItems(receipt.getItems());
		
		// If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. 
		// The result is the number of points earned
		final int itemLength = readItemsDesc(receipt.getItems());
		
		// 6 points if the day in the purchase date is odd.
		final int purchasedDate = readPurchasedDate(receipt.getPurchaseDate());
		
		// 10 points if the time of purchase is after 2:00pm and before 4:00pm.
		final int purchasedTime = readPurchasedTime(receipt.getPurchaseTime());
		
		UUID uuid = UUID.randomUUID();
		
		final int totalPoints = retailerPoint + roundDollar + numberOfItems + itemLength + purchasedDate + purchasedTime;
		
		ids.put(uuid.toString(), totalPoints);
		
		Map<String, Object> response = new HashMap<>();
        response.put("id", uuid.toString());
        return response;
	}
	
	/*
	 * Takes id as parameter and return the total points.
	 * */
	@GetMapping("/{id}/points")
	public Map<String, Integer> getId(@PathVariable String id) {
		Map<String, Integer> response = new HashMap<>();
		Integer totalPoints = ids.get(id);
        
        if (totalPoints != null) {
            response.put("points", totalPoints);
        } else {
            response.put("ID not found", -1);
        }
        
        return response;
		 
	}
	
	
	public int readRetailer(String retailer) {
		final String alphaNumeric = retailer.replaceAll("[^a-zA-Z0-9]", "");
		final int length = alphaNumeric.length();
		return length;
	}
	
	public int readTotal(double total) {
		int result = 0;
		if (total == (int)total) {
			result += 50;
		}
		
		if (total % 0.25 == 0) {
			result += 25;
		}
		
		return result;
	}
	
	public int readItems(List<Item> items) {
		final int numOfItems = items.size();
		
		return (numOfItems/2) * 5;
	}
	
	public int readItemsDesc(List<Item> items) {
		int result = 0;
		
		for(Item item : items) {
			String desc = item.getShortDescription().trim();
			if(desc.length() % 3 == 0) {
				double pointByItem = item.getPrice()*0.2;
				result += (int)Math.ceil(pointByItem);
			}
		}
		return result;
	}
	
	public int readPurchasedDate(String purchasedDate) {
		int result = 0;
		String date = purchasedDate.split("-")[2];
		final int dateInt = Integer.parseInt(date);
		if (dateInt %2 == 1) {
			result += 6;
		}
		
		return result;
	}
	
	public int readPurchasedTime(String purchasedTime) {
		int result = 0;
		String time = purchasedTime.split(":")[0];
		final int timeInt = Integer.parseInt(time);
		if (timeInt >= 14 && timeInt < 16) {
			result += 10;
		}
		
		return result;
	}
	
}











