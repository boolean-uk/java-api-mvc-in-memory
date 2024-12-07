package com.booleanuk.api.requests.models;

import java.net.InetSocketAddress;

public class Product {
    private static int nextID = 1;

    private int id;
    private String name;
    private String category;
    private double price;

    public Product() {
        this.id = nextID;
        nextID++;
    }

    public Product(String name, String category, double price) {
        this.id = nextID;
        nextID++;

        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
