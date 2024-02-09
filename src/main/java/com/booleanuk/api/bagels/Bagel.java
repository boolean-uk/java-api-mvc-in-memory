package com.booleanuk.api.bagels;

public class Bagel {
    private int id;
    private String type;
    private int price;

    public Bagel(int id, String type, int price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
