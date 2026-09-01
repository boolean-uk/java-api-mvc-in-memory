package com.booleanuk.api.product.model;

public class Product {

	private static int nextId = 0;

	private int id;
	private String name;
	private String category;
	private int price;

	public Product(String name, String category, int price){
		setId(++nextId);
		setName(name);
		setCategory(category);
		setPrice(price);
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCategory(){
		return this.category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public int getPrice(){
		return this.price;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public static int getCurrentId(){
		return nextId;
	}

}
