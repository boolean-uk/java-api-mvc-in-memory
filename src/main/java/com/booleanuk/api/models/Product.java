package com.booleanuk.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price) {
        this.id = nextId++;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
