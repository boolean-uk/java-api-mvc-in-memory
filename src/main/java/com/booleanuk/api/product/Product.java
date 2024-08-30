package com.booleanuk.api.product;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Product {
    private static int nextID=1;

    private int id;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price){
        this.id=nextID;
        nextID++;
        this.name=name;
        this.category=category;
        this.price=price;
    }

    public Product(){
        this.id=nextID;
        nextID++;
    }

}
