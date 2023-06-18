package com.wileyedge.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wileyedge.model.Change;
import com.wileyedge.model.Item;

@Component
public class VendingMachineView {
	private UserIO userIO;

	@Autowired
	public VendingMachineView(UserIO userIO) {
		this.userIO = userIO;
	}

	public int displayMenu(BigDecimal coins) {
		userIO.print("------- Vending Machine Menu -------");
		displayCoins(coins);
		userIO.print("1. Insert coins");
		userIO.print("2. Select an item");
		userIO.print("3. Exit");
		return userIO.readInt("Enter your choice: ");
	}

	public BigDecimal getInsertedCoins() {
		int quarters = userIO.readInt("Enter the number of quarters to insert: ");
		int dimes = userIO.readInt("Enter the number of dimes to insert: ");

		BigDecimal totalCents = BigDecimal.valueOf((quarters * 25) + (dimes * 10));
		BigDecimal totalDollars = totalCents.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

		return totalDollars;
	}

	public void displayCoins(BigDecimal coins) {
		userIO.print("======================================");
		userIO.print("Money in machine: $" + coins);
		userIO.print("======================================");
	}

	public void displayErrorMessage(String errorMessage) {
		userIO.print("Error: " + errorMessage);
	}

	public void displayMessage(String message) {
		userIO.print(message);
	}

	public void displayItems(List<Item> items) {
		userIO.print("Available Items:");
		for (Item item : items) {
			userIO.print(item.getId() + "." + item.getName());
			userIO.print("$" + item.getCost());
			userIO.print(item.getInventoryCount() + " remaining.");
			System.out.println("--------------------");
		}
	}

	public int getChoice(String message) {
		return userIO.readInt(message);
	}

	public void displayChange(Change change) {
	    userIO.print("Here's your change:");

	    int quarters = change.getQuarters();
	    int dimes = change.getDimes();
	    int nickels = change.getNickels();
	    int pennies = change.getPennies();

	    if (quarters > 0) {
	        userIO.print("Quarters: " + quarters);
	    }
	    if (dimes > 0) {
	        userIO.print("Dimes: " + dimes);
	    }
	    if (nickels > 0) {
	        userIO.print("Nickels: " + nickels);
	    }
	    if (pennies > 0) {
	        userIO.print("Pennies: " + pennies);
	    }
	}

}
