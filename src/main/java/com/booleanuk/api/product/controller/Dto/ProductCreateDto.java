package com.booleanuk.api.product.controller.Dto;

public class ProductCreateDto {
    private String name;
    private String category;
    private int price;

    public ProductCreateDto() {

    }
    public ProductCreateDto(String name, int id, int price, String category) {
        setName(name);
        setCategory(category);
        setPrice(price);
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

