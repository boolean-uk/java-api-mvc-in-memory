package com.booleanuk.api.products;

public class Product {
    private static int nextId = 1;
    private final int id;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price){
        this.id = nextId;
        nextId+=1;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String newCategory){
        this.category = newCategory;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int newPrice){
        this.price = newPrice;
    }
}
