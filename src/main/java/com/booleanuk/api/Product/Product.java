package com.booleanuk.api.Product;

public class Product {
    private int id;
    private String name;
    private String category;
    private Integer price;

    public Product(int id, String name, String category, Integer price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;

    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) {this.category = category; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) {this.price = price; }
}
