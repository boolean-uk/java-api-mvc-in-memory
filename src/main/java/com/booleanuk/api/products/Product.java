package com.booleanuk.api.products;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;

public class Product {
    private static int nextId=1;
    private int id;
    private String name;
    private String category;
    @NumberFormat
    private int price;

    public Product(String name, String category, int price) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.category = category;
        this.price = price;
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
