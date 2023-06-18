package com.wileyedge.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	private int id;
	private String name;
	private BigDecimal cost;
	@JsonProperty("count")
	private int inventoryCount;
	
    public Item() {
    }

	public Item(int id, String name, BigDecimal cost, int inventoryCount) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.inventoryCount = inventoryCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	@Override
	public String toString() {
		return id + "." + name + " $" + cost + " " + inventoryCount + " remaining";
	}

}
