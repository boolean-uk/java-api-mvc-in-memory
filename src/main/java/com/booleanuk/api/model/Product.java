package com.booleanuk.api.model;

import java.net.Inet4Address;

public class Product {
    private int id;
    private String name;
    private String category;
    private int price;

    public Product(int id, String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product() {}

    public int getId() {
        return this.id;
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
}
