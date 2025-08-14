package com.booleanuk.api.repositories;

import com.booleanuk.api.bagels.Bagel;
import com.booleanuk.api.models.Product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;
    private int id;

    public ProductRepository() {
        this.products = new ArrayList<>();

        //this.products.add(new Product(this.id++, "Bagel", "Food",  20));
        //this.products.add(new Product(this.id ++, "Coffee", "Food", 15));
    }

    public void create(Product product) {
        this.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> findAll() {
        return this.products;
    }

    public Product find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public void updateId(int id) {
        for (int i = id; i < products.size() - 1; i ++) {
            products.get(i+1).setId(i);
        }
    }
}
