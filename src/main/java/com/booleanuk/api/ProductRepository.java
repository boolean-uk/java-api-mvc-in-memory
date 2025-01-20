package com.booleanuk.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductRepository {
    private List<Product> products = new ArrayList<>() {
        {
            add(new Product("How to build APIs", "Book", 1500));
            add(new Product("Grey bat", "Sports", 3000));
        }
    };

    List<Product> getAll() {
        return products;
    }

    List<Product> getCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().toLowerCase().equals(category.toLowerCase())) {
                filtered.add(p);
            }
        }
        // return null if no items with this category
        if (filtered.size() == 0) {
            return null;
        }
        return filtered;
    }

    Product getProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    Product create(Product product) {
        // check if product with same name exists
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                return null;
            }
        }

        products.add(product);
        return product;
    }

    Product update(int id, Product updated) {
        Product product = getProduct(id);
        if (product != null) {
            // check if product with updated name already exists
            for (Product p : products) {
                if (p.getName().equals(updated.getName())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Product with provided name already exists.");
                }
            }
            product.setName(updated.getName());
            product.setCategory(updated.getCategory());
            product.setPrice(updated.getPrice());
        }
        return product;
    }

    Product delete(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                products.remove(p);
                return p;
            }
        }
        return null;
    }
}
