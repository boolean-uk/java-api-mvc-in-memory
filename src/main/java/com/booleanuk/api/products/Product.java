package com.booleanuk.api.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    public static int idCounter = 1;
    private String name;
    private String category;
    private int price;
    private int id;

    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = idCounter;
        idCounter++;
    }

    public Product() {
        this.id = idCounter;
        idCounter++;
    }

    public void reduceCounter() {
        idCounter--;
    }
}
