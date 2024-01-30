package com.booleanuk.api;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    
    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAll(String category) {
        if (category == null){
            return this.products;
        }
        return this.products.stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).toList();
    }

    public Product getOne(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product create(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
            return product;
        }
        return null;
    }

    public Product update(int id, Product product) {
        Product productToUpdate = this.getOne(id);
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setPrice(product.getPrice());
        }
        return this.getOne(id);
    }

    public Product delete(int id) {
        Product productToDelete = this.getOne(id);
        if (productToDelete != null) {
            this.products.remove(productToDelete);
        }
        return productToDelete;
    }
}
