package com.booleanuk.api.bagels.core;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Car", "Vehicle", 500.00));
        this.products.add(new Product("Bike", "Vehicle", 100.00));
        this.products.add(new Product("Cake", "Food", 10.00));
        this.products.add(new Product("Pizza", "Food", 5.00));
    }

    public ArrayList<Product> getAll() {
        return this.products;
    }
    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public Product create(Product product) {
        this.products.add(product);
        return product;
    }
    public Product updateProduct(int id, Product product) {
        for (Product product1 : this.products) {
            if (product1.getId() == id) {
                product1.setName(product.getName());
                product1.setCategory(product.getCategory());
                product1.setPrice(product.getPrice());
                return product1;
            }
        }
        return null;
    }
    public List<Product> deleteProduct(int id) {
        Product productToRemove = null;
        for (Product product1 : this.products) {
            if (product1.getId() == id) {
                productToRemove = product1;
                break;
            }
        }
        if (productToRemove != null) {
            this.products.remove(productToRemove);
            return this.products;
        } else {
            return null;
        }

    }
}
