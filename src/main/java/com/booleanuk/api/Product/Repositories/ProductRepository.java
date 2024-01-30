package com.booleanuk.api.Product.Repositories;

import com.booleanuk.api.Product.Models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("GOT", "Book", 637));
        this.products.add(new Product("MI", "Movie", 9));
    }

    public ArrayList<Product> getAll(String category) {
        ArrayList<Product> result = new ArrayList<>();

        if (category == null) {
            return products;
        }

        for (Product product: products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }

        if (products.isEmpty() || result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found");
        }


        return result;
    }

    public Product getOne(int id){
        for(Product product: products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product create(Product product){
        for (Product prod: products) {
            if (product.getName().equals(prod.getName())){
                // Teapot is purely for comedy
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
            }
        }
        products.add(product);
        for (Product prod: products) {
            if (product.getName().equals(prod.getName())){
                return prod;
            }
        }
        return null;
    }

    public Product update(int id, Product product) {
        for(Product prod: products) {
            if (prod.getId() == id) {
                if (prod.getName().equals(product.getName())
                 && prod.getCategory().equals(product.getCategory())
                 && prod.getId() == product.getPrice()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
                }
                prod.setName(product.getName());
                prod.setCategory(product.getCategory());
                prod.setPrice(product.getPrice());
                return prod;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product delete(int id) {
        for(Product product: products) {
            if (product.getId() == id) {
                products.remove(product);
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}
