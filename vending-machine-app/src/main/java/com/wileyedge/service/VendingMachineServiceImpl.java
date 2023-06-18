package com.wileyedge.service;

import com.wileyedge.dao.ItemDao;
import com.wileyedge.exception.InsufficientFundsException;
import com.wileyedge.exception.NoItemInventoryException;
import com.wileyedge.model.Change;
import com.wileyedge.model.Item;
import com.wileyedge.model.VendingMachine;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {
	private final VendingMachine vendingMachine;
	private final ItemDao itemDao;

	@Autowired
	public VendingMachineServiceImpl(VendingMachine vendingMachine, ItemDao ItemDao) {
		this.vendingMachine = vendingMachine;
		this.itemDao = ItemDao;
	}

	public void insertCoins(BigDecimal coins) {
		vendingMachine.insertCoins(coins);
	}

	public BigDecimal getCoinsInMachine() {
		return vendingMachine.getCoinsInMachine();
	}
	
	private Change returnChange(BigDecimal itemCost) {
		return vendingMachine.returnChange(itemCost);
	}
	
	@Override
	public List<Item> getItems() {
		return itemDao.getItems();
	}

	@Override
	public BigDecimal getItemCost(int itemId) {
		Item item = itemDao.getItemById(itemId);
		return item != null ? item.getCost() : BigDecimal.ZERO;
	}

	@Override
	public Change vendItem(int itemId) throws InsufficientFundsException, NoItemInventoryException {
	    Item item = itemDao.getItemById(itemId);
	    if (item == null || item.getInventoryCount() <= 0) {
	        throw new NoItemInventoryException("Item does not exist");
	    }

	    BigDecimal itemCost = item.getCost();
	    BigDecimal insertedAmount = getCoinsInMachine();

	    if (insertedAmount.compareTo(itemCost) >= 0) {
	        // Sufficient funds, proceed with vending
	        itemDao.updateInventoryCount(item);
	        return returnChange(itemCost);
	    } else {
	        // Insufficient funds
	        throw new InsufficientFundsException("Insufficient funds");
	    }
	}
}
