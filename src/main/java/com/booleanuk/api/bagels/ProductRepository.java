package com.booleanuk.api.bagels;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public Product create(String name, int price, String category) {
        Product product = new Product(this.idCounter++, name, price, category);
        this.data.add(product);
        return product;
    }

    public List<Product> findAll() {
        return this.data;
    } // we are going to use this method in the Controller

    public Product find(int id) { //and for this we are using this method
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(); //i dont know what happens if it does not find it... but here is t null? if we dont find it
    }
    public Product update(String name, int price, String category, int id){
        for( Product product: this.data ){
            if (product.getId() == id){
                product.setCategory(category);
                product.setName(name);
                product.setPrice(price);
                return product;
            }
        }
        return null; //here we return null if we dont find it
    }
    public Product delete(int id){
            for ( Product product: this.data){
                if(product.getId() == id){
                    this.data.remove(id);
                    return product;
                }
            }
            return null;
        }

    }

