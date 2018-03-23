package com.tarena.entity;

public class Item {

	private int id;
	private int orderId;
	private int productId;
	private String productName;
	private double dangPrice;
	private int productNum;
	private double amount;
		
	public Item() {
		super();
	}

	public Item(int id, int orderId, int productId, String productName,
			double dangPrice, int productNum, double amount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.dangPrice = dangPrice;
		this.productNum = productNum;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getDangPrice() {
		return dangPrice;
	}

	public void setDangPrice(double dangPrice) {
		this.dangPrice = dangPrice;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
