package com.booleanuk.api.extension;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private static int nextID = 1;
    private String name;
    private String category;
    private int price;
    private int id;


    Product(String name, String category, int price) {
        this.id = nextID;
        nextID++;
        this.name = name;
        this.category = category;
        this.price = price;

    }

}
