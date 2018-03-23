package com.tarena.entity;

import java.util.List;

public class Cart{
	private int userId;
	private List<ItemInCart> items;
	private double totalPrice;
	private double totalSave;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public double getTotalSave() {
		return totalSave;
	}
	public void setTotalSave(double totalSave) {
		this.totalSave = totalSave;
	}
	public List<ItemInCart> getItems() {
		return items;
	}
	public void setItems(List<ItemInCart> items) {
		this.items = items;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
