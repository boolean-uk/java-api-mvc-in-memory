package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public ProductRepository() {
        create("Milk", "Dairy", 10);
        create("Toast", "Bread", 5);
        create("Eggs", "Hatchables", 15);
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category, price);
        this.data.add(product);
        return product;
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product getOne(int id) { return find(id); }

    public Product update(int id, Product product) {
        Product productUpdated = find(id);
        if(productUpdated.getName().equalsIgnoreCase(product.getName())) {
            productUpdated.setCategory(product.getCategory());
            productUpdated.setPrice(product.getPrice());
            return productUpdated;
        }
        else if(findAll().stream().anyMatch(x -> x.getName().equalsIgnoreCase(product.getName()))) return null;
        productUpdated.setName(product.getName());
        productUpdated.setCategory(product.getCategory());
        productUpdated.setPrice(product.getPrice());
        return productUpdated;
    }

    public Product delete(int id) {
        Product product = find(id);
        if(product == null) return null;
        this.data.remove(product);
        return product;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
