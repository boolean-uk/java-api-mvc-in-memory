package com.booleanuk.api.products;

import com.booleanuk.api.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ProductRepository {
    private HashMap<Integer, Product> products;

    public ProductRepository() {
        this.products = new HashMap<>();
    }

    public HashMap<Integer, Product> getAll(String category) {
        HashMap<Integer, Product> productsOfCategory = new HashMap<>();
        for (Product product : this.products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsOfCategory.put(product.getId(), product);
            }
        }
        if (category == null) {
            productsOfCategory = this.products;
        }
        if (productsOfCategory.isEmpty()) {
            throw new ProductNotFoundException("Not found.");
        }
        return productsOfCategory;
    }

    public Product getOne(int id) throws ProductNotFoundException {
        if (!this.products.containsKey(id)) {
            throw new ProductNotFoundException("Product not found.");
        }
        return this.products.get(id);
    }

    public Product create(Product product) {
        if (this.findByName(product.getName()) != null) {
            product.reduceCounter();
            throw new ProductAlreadyExistsException("Product with provided name already exists");
        }
        this.products.put(product.getId(), product);
        return product;
    }

    public Product update(int id, Product product) {
        if (this.products.containsKey(id)) {
            if (this.findByName(product.getName()) == null ||
                    (this.findByName(product.getName()) != null &&
                            this.findByName(product.getName()).getId() == id)) {
                Product productToUpdate = this.products.get(id);
                productToUpdate.setName(product.getName());
                productToUpdate.setCategory(product.getCategory());
                productToUpdate.setPrice(product.getPrice());
                return productToUpdate;
            }
            product.reduceCounter();
            throw new ProductAlreadyExistsException("Product with provided name already exists");
        }
        product.reduceCounter();
        throw new ProductNotFoundException("Product not found.");
    }

    public Product delete(int id) {
        if (!this.products.containsKey(id)) {
            throw new ProductNotFoundException("Product not found.");
        }
        return this.products.remove(id);
    }

    public Product findByName(String name) {
        for (Product product : this.products.values()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
