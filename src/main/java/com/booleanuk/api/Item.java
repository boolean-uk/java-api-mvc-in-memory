package com.booleanuk.api;

public class Item {
    private static int nextID = 1;

    private int id;
    private String name;
    private String category;
    private int price;

    public Item( String name, String type, int price) {
        this.id = nextID++;
        this.name = name;
        this.category = type;
        this.price = price;
    }

    public int getId() {
        return id;
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
