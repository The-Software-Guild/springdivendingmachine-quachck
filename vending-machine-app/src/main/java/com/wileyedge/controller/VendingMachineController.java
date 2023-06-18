package com.wileyedge.controller;

import com.wileyedge.exception.InsufficientFundsException;
import com.wileyedge.exception.NoItemInventoryException;
import com.wileyedge.model.Change;
import com.wileyedge.model.Item;
import com.wileyedge.service.VendingMachineService;
import com.wileyedge.view.UserIO;
import com.wileyedge.view.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VendingMachineController {
	private final VendingMachineService vendingMachineService;
	private final VendingMachineView view;

	@Autowired
	public VendingMachineController(VendingMachineService vendingMachineService, VendingMachineView view) {
		this.vendingMachineService = vendingMachineService;
		this.view = view;
	}

	public void run() {
		boolean keepRunning = true;
		while (keepRunning) {
			int choice = view.displayMenu(vendingMachineService.getCoinsInMachine());
			switch (choice) {
			case 1:
				insertCoins();
				break;
			case 2:
				selectItem();
				break;
			case 3:
				exit();
				keepRunning = false;
				break;
			default:
				view.displayMessage("Invalid choice. Please try again.");
			}
		}
	}

	private void exit() {
		view.displayMessage("Thanks for using the vending machine");
	}

	private void insertCoins() {
		view.displayMessage("***** This machine only takes quarters and dimes *****");
		BigDecimal insertedCoins = view.getInsertedCoins();
		vendingMachineService.insertCoins(insertedCoins);
	}

	private void selectItem() {
		if (vendingMachineService.getCoinsInMachine().equals(BigDecimal.ZERO)) {
			view.displayErrorMessage("Please insert coins to use the vending machine.");
			return;
		}

		List<Item> items = vendingMachineService.getItems();
		view.displayItems(items);

		int choice = view.getChoice("Enter the item number to select: ");

		try {
			BigDecimal cost = vendingMachineService.getItemCost(choice);
			if (cost == null) {
				view.displayErrorMessage("Invalid item number. Please try again.");
				return;
			}

			if (cost.compareTo(vendingMachineService.getCoinsInMachine()) > 0) {
				view.displayErrorMessage("Insufficient funds. Please insert more coins.");
				return;
			}

			Change change = vendingMachineService.vendItem(choice);
			view.displayChange(change);
			view.displayMessage("Enjoy your item!");
		} catch (NoItemInventoryException e) {
			view.displayErrorMessage("Item is out of stock. Please select another item.");
		} catch (InsufficientFundsException e) {
			view.displayErrorMessage("Insufficient funds. Please insert more coins.");
		}
	}

}
