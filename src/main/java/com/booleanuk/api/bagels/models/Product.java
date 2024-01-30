package com.booleanuk.api.bagels.models;

public class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String category;
    private double price;


    public Product() {
        this.id = nextId++;

    }

    public Product(String name, String category, double price) {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}