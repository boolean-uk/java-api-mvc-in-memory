package com.booleanuk.api.products.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Product() {}
}
