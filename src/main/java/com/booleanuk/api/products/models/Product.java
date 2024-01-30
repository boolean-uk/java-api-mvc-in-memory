package com.booleanuk.api.products.models;

public class Product {
    private static int nextId = 0;

    private int id;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price){
        this.id = nextId++;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
