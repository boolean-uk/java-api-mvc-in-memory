package com.booleanuk.api.products;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private ArrayList<Product> products;

    public ProductRepository(){

        this.products = new ArrayList<>();

    }

    public ArrayList<Product> getAll(){
        return this.products;
    }

    public Product getOne(int id){

        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product createOne(Product product) {
        product.setId(this.idCounter++);
        this.products.add(product);
        return product;
    }

    public Product updateOne(int id, Product updatedProduct) {
        Product product = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());

        return product;
    }

    public void deleteOne(int id) {
        Product product = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        this.products.remove(product);
    }


}