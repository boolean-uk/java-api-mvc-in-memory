package com.booleanuk.api.product.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAll() {
        if (products.isEmpty()) {
            return null;
        }
        return this.products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getOne(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(Product product) {
        for (Product value : products) {
            if (value.getName().equals(product.getName())) {
                return null;
            }
        }
        products.add(product);
        return product;
    }

    public Product update(int id, Product product) {
        Product updatedProduct = this.getOne(id);
        if (updatedProduct != null) {
            if (updatedProduct.getName().equals(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
            }
            updatedProduct.setName(product.getName());
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setPrice(product.getPrice());
        }
        return updatedProduct;
    }

    public Product delete(int id) {
        Product deletedProduct = this.getOne(id);
        if (deletedProduct != null) {
            products.remove(deletedProduct);
            return deletedProduct;
        }
        return null;
    }
}
