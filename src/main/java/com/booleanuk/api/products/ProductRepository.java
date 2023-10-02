package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private int idCounter = 1;
    private Map<Integer, Product> products = new HashMap<>();

    public Product create(String name, String category, int price) {
        Product product = new Product(idCounter, name, category, price);
        products.put(idCounter, product);
        idCounter++;
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product find(int id) {
        return products.get(id);
    }

    public Product update(int id, String name, String category, int price) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            return product;
        }
        return null;
    }

    public Product delete(int id) {
        return products.remove(id);
    }
}
