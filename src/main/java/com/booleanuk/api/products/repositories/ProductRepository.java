package com.booleanuk.api.products.repositories;

import com.booleanuk.api.products.models.Product;

import java.util.ArrayList;

public class ProductRepository {
    int idCounter = 0;
    ArrayList<Product> data = new ArrayList<>();

    public Product createProduct(String name, String category, int price) {
        Product p = new Product(name, category, price, idCounter++);
        data.add(p);
        return p;
    }

    public ArrayList<Product> getAll() {
        return new ArrayList<>(data);
    }

    public Product deleteProduct(Product product) {
        data.remove(product);
        return product;
    }
}
