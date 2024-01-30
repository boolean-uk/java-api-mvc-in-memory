package com.booleanuk.api.requests;

public class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String type;
    private int price;

    public Product(String name, String type, int price) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
