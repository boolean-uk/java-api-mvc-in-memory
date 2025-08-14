package com.booleanuk.api.product.models;

public class Product {
    private static int nextId;
    private String name;
    private String category;
    private String price;
    private int id;

    public Product(String name, String category, String price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
