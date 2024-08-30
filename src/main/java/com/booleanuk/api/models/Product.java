package com.booleanuk.api.models;

import java.util.Objects;

public class Product {
    private static int nextID = 0;
    private int id;
    private String name;
    private String type;
    private int price;

    public Product(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.id = nextID;
        nextID++;

    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
