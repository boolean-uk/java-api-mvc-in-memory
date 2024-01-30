package com.booleanuk.api.bagels.model;


import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Product {

    private static int nextID = 0;
    @Getter
    private int id = 0;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = nextID;
        nextID++;
    }

}
