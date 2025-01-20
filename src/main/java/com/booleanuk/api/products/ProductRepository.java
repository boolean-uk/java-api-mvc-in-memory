package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data;

    public ProductRepository() {
        data = new ArrayList<>();
        create("Bagel", "Food", 12);
        create("Apple", "Food", 7);
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category, price);
        this.data.add(product);
        return product;
    }

    public Product create(Product product) {
        if (!nameExists(product.getName())) {
            return this.create(product.getName(), product.getCategory(), product.getPrice());
        }
        return null;
    }

    public List<Product> findAll(String category) {
        return this.data.stream()
                .filter(p -> p.getCategory().equals(category))
                .toList();
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product update(int id, Product product) {
        if (nameExists(product.getName(), id)) {
            return null;
        }
        Product productToUpdate = find(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productToUpdate;
    }

    public Product delete(int id) {
        Product productToDelete = find(id);
        data.remove(productToDelete);
        return productToDelete;
    }

    private boolean nameExists(String name) {
        return !this.data.stream()
                .filter(p -> p.getName().equals(name))
                .toList().isEmpty();
    }

    private boolean nameExists(String name, int excludeId) {
        return !this.data.stream()
                .filter(p -> p.getName().equals(name) && p.getId() != excludeId)
                .toList().isEmpty();
    }
}
