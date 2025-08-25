package com.booleanuk.api.product.model.pojo;

import com.booleanuk.api.product.controller.Dto.ProductCreateDto;

public class Product {
    private static int nextId = 1;

    private String name;
    private String category;
    private int price;
    private int id;

    public Product(String name, String category, int price) {
        setId(nextId++);
        setName(name);
        setCategory(category);
        setPrice(price);
    }

    public Product(ProductCreateDto productCreateDto) {
        this(productCreateDto.getName(), productCreateDto.getCategory(), productCreateDto.getPrice());
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
