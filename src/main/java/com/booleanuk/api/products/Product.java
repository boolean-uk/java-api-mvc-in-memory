package com.booleanuk.api.products;

public class Product {

    private static int CURRENT_ID = 1;

    private int id;
    private String name;
    private String category;
    private int price;

    public Product() {
    }

    public Product(String name, String category, int price) {
        id = CURRENT_ID++;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(Product instance){
        this(instance.getName(), instance.getCategory(), instance.getPrice());
    }

    //readonly
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
