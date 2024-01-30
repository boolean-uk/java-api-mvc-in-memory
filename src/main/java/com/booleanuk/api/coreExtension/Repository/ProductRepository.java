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
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    public Boolean isPresent(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return true;
            }
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
            if (product.getName().equalsIgnoreCase(name) && product.getId() != id) {
                return true;
            }
        }
        return false;
    }
    public Product create(Product product) {
        for (Product existingProduct : this.products) {
            if (existingProduct.getName().equalsIgnoreCase(product.getName())) {
                throw new IllegalArgumentException("Product with provided name already exists");
            }
        }
        this.products.add(product);
        return product;
    }
    public Product updateProduct(int id, Product product) {
        for (Product existingProduct : this.products) {
            if (existingProduct.getId() == id) {
                existingProduct.setName(product.getName());
                existingProduct.setCategory(product.getCategory());
                existingProduct.setPrice(product.getPrice());
                return existingProduct;
            }
        }
        return null;
    }
    public List<Product> deleteProduct(int id) {
        Product productToRemove = null;
        for (Product product : this.products) {
            if (product.getId() == id) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            this.products.remove(productToRemove);
            return new ArrayList<>(this.products); // Returning a copy of the list
        } else {
            return null;
        }
    }
}
