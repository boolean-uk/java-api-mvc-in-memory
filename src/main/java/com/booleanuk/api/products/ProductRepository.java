package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public void create(String name, String type, int price) {
        Product product = new Product(this.idCounter++, name, type, price);
        this.data.add(product);
    }

    public  List<Product> findAll() { return this.data; }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product remove(int id) {
        Product product = find(id);
        if (data.contains(product)) {
            data.remove(product);
            return product;
        }
        return null;
    }

    public Product update(int id, Product updatedProduct) {
        Product product = find(id);
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        return product;
    }
}
