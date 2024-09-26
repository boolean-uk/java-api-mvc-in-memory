package com.booleanuk.api.bagels;

public class Product {
    private int id;
    private String name;
    private int price;
    private String category;

    public Product(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
    public String getName(){
        return name;
    }
    public void setCategory(String category){
         this.category = category;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
}
