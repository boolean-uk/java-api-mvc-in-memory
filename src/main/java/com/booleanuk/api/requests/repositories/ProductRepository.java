package com.booleanuk.api.requests.repositories;

import com.booleanuk.api.requests.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private int idCounter;
    private List<Product> products;

    public ProductRepository() {
        this.idCounter = 1;
        this.products = new ArrayList<>();
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category,  price);
        this.products.add(product);
        return product;
    }

    public List<Product> getAll(){
        return this.products;
    }

    public Product getOne(int id){
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product update(int id, Product product){
        Product productToUpdate = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(productToUpdate != null){
            productToUpdate.setName(product.getName());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setPrice(product.getPrice());
        }
        return productToUpdate;
    }

    public Product delete(int id){
        Product productToDelete = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(productToDelete != null){
            this.products.remove(productToDelete);
        }
        return productToDelete;
    }
}

