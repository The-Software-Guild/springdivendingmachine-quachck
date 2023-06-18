package com.wileyedge.service;

import java.math.BigDecimal;
import java.util.List;

import com.wileyedge.exception.InsufficientFundsException;
import com.wileyedge.exception.NoItemInventoryException;
import com.wileyedge.model.Change;
import com.wileyedge.model.Item;

public interface VendingMachineService {
	void insertCoins(BigDecimal coins);

	BigDecimal getCoinsInMachine();
	
	List<Item> getItems();
	
	public BigDecimal getItemCost(int itemId);
	
	public Change vendItem(int itemId) throws InsufficientFundsException, NoItemInventoryException;
}
