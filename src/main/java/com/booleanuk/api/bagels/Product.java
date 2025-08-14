package com.booleanuk.api.bagels;

public class Product {
    public static Integer nextID = 0;
    private String name;
    private Integer price;
    private String category;
    private int id;

    public Product(String name, Integer price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.id = nextID++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Integer getNextID() {
        return nextID;
    }

    public static void setNextID(Integer nextID) {
        Product.nextID = nextID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
