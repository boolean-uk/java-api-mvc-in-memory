package com.booleanuk.api.products;

public class Product {
    private static int nextID = 0;
    private int id;
    private String name;
    private String category;
    private int price;

    public Product() {

    }

    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;

        this.id = nextID;
        nextID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
