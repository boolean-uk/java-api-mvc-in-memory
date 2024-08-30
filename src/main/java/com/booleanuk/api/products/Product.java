package com.booleanuk.api.products;

public class Product extends ProductRequest {
    private static int nextID = 1;
    private final int id;

    public Product(ProductRequest productRequest){
        super(productRequest.getName(), productRequest.getCategory(), productRequest.getPrice());
        this.id = nextID++;
    }

    public int getId(){
        return id;
    }
}

