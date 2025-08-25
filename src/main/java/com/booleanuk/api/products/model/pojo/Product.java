package com.booleanuk.api.products.model.pojo;

import com.booleanuk.api.products.controller.dto.ProductDto;

public class Product {
    private static int nextId = 0;
    private int id;
    private String name;
    private String category;
    private int price;

    public Product() {

    }

    public Product(String name, String category, int price) {
        setId(nextId++);
        setName(name);
        setCategory(category);
        setPrice(price);
    }

    public Product(ProductDto productCreateDto) {
        this(productCreateDto.getName(), productCreateDto.getCategory(), productCreateDto.getPrice());
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

    public void setId(int id) {
        this.id = id;
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
