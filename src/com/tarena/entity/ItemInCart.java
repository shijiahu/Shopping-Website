package com.tarena.entity;

public class ItemInCart {
	private int productId;
	private String productName;
	private int productNum;
	private double fixedPrice;
	private double dangPrice;
	private int productIntenvory;
	private double amount;
	private int deleted;
	
	public ItemInCart() {
		super();
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
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public double getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public double getDangPrice() {
		return dangPrice;
	}
	public void setDangPrice(double dangPrice) {
		this.dangPrice = dangPrice;
	}
	public int getProductIntenvory() {
		return productIntenvory;
	}
	public void setProductIntenvory(int productIntenvory) {
		this.productIntenvory = productIntenvory;
	}

	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
