package com.booleanuk.api.Product.Models;

public class Product {
    private static int nextID = 1;
    private int id;
    private String name;
    private String category;
    private int price;

    public Product() {
        this.id = nextID;
        nextID++;
    }

    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;

        this.id = nextID;
        nextID++;
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

    public int getId() {
        return id;
    }
}
