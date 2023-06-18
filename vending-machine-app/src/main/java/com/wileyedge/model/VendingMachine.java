package com.wileyedge.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class VendingMachine {
	private BigDecimal coinsInMachine;

	public VendingMachine() {
		coinsInMachine = new BigDecimal(0);
	}

	public void insertCoins(BigDecimal coins) {
		coinsInMachine = coinsInMachine.add(coins);
	}

	public BigDecimal getCoinsInMachine() {
		return coinsInMachine;
	}

	public Change returnChange(BigDecimal itemCost) {
		BigDecimal changeAmount = coinsInMachine.subtract(itemCost);
		BigDecimal amountInPennies = changeAmount.multiply(new BigDecimal(100));

		Change change = new Change(amountInPennies);
		coinsInMachine = BigDecimal.ZERO; // Reset coins in machine

		return change;
	}
}
