
package com.booleanuk.api.product.model;

public class ProductCreateDto {


	private String name;
	private String category;
	private int price;

	public ProductCreateDto(String name, String category, int price){
		setName(name);
		setCategory(category);
		setPrice(price);
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

}
