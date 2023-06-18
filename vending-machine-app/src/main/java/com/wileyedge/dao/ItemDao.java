package com.wileyedge.dao;

import java.util.List;

import com.wileyedge.model.Item;

public interface ItemDao {
	List<Item> getItems();
	
	public Item getItemById(int itemId);
	
	public void updateInventoryCount(Item item);
}
