package com.booleanuk.api.products;

public class ProductRequest {
    private String name;
    private String category;
    private int price;

    // TODO Try Lombok to generate getters and setters

    public ProductRequest(String productName, String productCategory, int productPrice){
        this.name = productName;
        this.category = productCategory;
        this.price = productPrice;
    }

    public String getName(){
        return this.name;
    }

    public String getCategory(){
        return this.category;
    }

    public int getPrice(){
        return this.price;
    }

    public void setName(String newProductName){
        this.name = newProductName;
    }

    public void setCategory(String newProductCategory){
        this.category = newProductCategory;
    }

    public void setPrice(int newProductPrice){
        this.price = newProductPrice;
    }
}
