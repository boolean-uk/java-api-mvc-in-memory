package com.booleanuk.api.models;

public class Bagel extends Product {
    private String name;
    private String type;
    private int price;

    public Bagel(String name, int price) {
        super(name, "Bagel", price);
    }

    public Bagel() {

    }
}
