package com.booleanuk.api.requests.models;

public class Product {
    private String name;
    private String category;
    private int price;
    private int id;

    public Product(String name, String category, int price, int id) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
