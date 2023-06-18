package com.wileyedge.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wileyedge.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	@Value("${inventory.file.path}")
	private String inventoryFile;

	private ObjectMapper objectMapper;

	@Autowired
	public ItemDaoImpl(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public List<Item> getItems() {
		List<Item> items = new ArrayList<>();

		try {
			File file = new File(inventoryFile);
			Item[] itemArray = objectMapper.readValue(file, Item[].class);
			items = Arrays.asList(itemArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Item getItemById(int itemId) {
		List<Item> items = getItems();
		for (Item item : items) {
			if (item.getId() == itemId) {
				return item;
			}
		}
		return null;
	}

	@Override
	public void updateInventoryCount(Item item) {
	    List<Item> items = getItems();
	    for (Item currentItem : items) {
	        if (currentItem.getId() == item.getId()) {
	            int currentCount = currentItem.getInventoryCount();
	            currentItem.setInventoryCount(currentCount - 1);
	            writeItemsToFile(items);
	            return;
	        }
	    }
	}
	
    public void writeItemsToFile(List<Item> items) {
        try {
            File file = new File(inventoryFile);
            objectMapper.writeValue(file, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
