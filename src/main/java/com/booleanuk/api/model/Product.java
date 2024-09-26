package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.ThreadLocalRandom;

public class Product {
    public String name;
    public String category;
    public int price;

    private final int id;

    @JsonCreator
    public Product(String name, String category, int price) {
        id = ThreadLocalRandom.current().nextInt();

        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void merge(final Product other) {
        name = other.name;
        category = other.category;
        price = other.price;
    }

    @JsonIgnore
    public boolean isValidProduct() {
        return name != null && category != null && price > 0;
    }

    public int getId() {
        return id;
    }
}
