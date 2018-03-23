package com.tarena.entity;

public class Category {
	private int id;
	private int turn;
	private String enName;
	private String name;
	private String description;
	private int parentId;
	public Category() {
		super();
	}
	public Category(int id, int turn, String enName, String name,
			String description, int parentId) {
		super();
		this.id = id;
		this.turn = turn;
		this.enName = enName;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
