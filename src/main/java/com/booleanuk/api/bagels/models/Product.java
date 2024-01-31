package com.booleanuk.api.bagels.models;

public class Product {
    private static int counter = 0;
    private int id;
    private String name;
    private String category;
    private int price;

    public Product(){

    }
    public Product(String name, int price, String category) {
        this.id = counter++;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
