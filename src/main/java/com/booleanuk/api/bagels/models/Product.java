package com.booleanuk.api.bagels.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
    private static int nextID = 1;

    private int id;
    private String name;
    private String category;
    private int price;

    public Product(String name, String category, int price) {

        this.id = nextID;
        nextID++;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(){
        this.id = nextID;
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

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}


