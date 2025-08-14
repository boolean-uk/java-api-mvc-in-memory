package com.booleanuk.api.models;

public class Product {

    private static int nextId = 0;
    private int id;
    private String type;
    private String category;
    private int price;

    public Product(int id, String type, String category, int price) {
        this.id = nextId ++;
        this.type = type;
        this.category = category;
        this.price = price;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Product.nextId = nextId;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
