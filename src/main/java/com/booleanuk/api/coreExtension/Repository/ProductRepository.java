package com.booleanuk.api.coreExtension.Repository;

import com.booleanuk.api.coreExtension.Item.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (this.products.isEmpty()){
            return null;
        }
        return this.products;
    }
    public ArrayList<Product> getCategory(String category) {
        for (Product product : this.products) {
            if (product.getCategory().equals(category)) {
                return product;
            }
            return null;
        }
        return null;
    }
    public Boolean isPresent(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return true;
            }
            return false;
        }
        return false;
    }
    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public Boolean getNamesAndId(String name, int id) {
        for (Product product : this.products) {
            if (Objects.equals(product.getName(), name) && !Objects.equals(product.getId(), id));
            return false;
        }
        return true;
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
