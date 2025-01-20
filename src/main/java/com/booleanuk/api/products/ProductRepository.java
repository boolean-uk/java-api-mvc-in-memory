package com.booleanuk.api.products;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product createProduct(Product product) {
        for (Product sameNameProduct : this.products) {
            if (product.getName().equals(sameNameProduct.getName())) {
                return null;
            }
        }
        this.products.add(product);
        return product;
    }

    public List<Product> getAll(String category){
        if (category == null) {
            return this.products;
        }
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory().equals(category)) {
                matchingProducts.add(product);
            }
        }
        if (matchingProducts.isEmpty()) {
            return null;
        }
        return matchingProducts;
    }


    public Product getOneProduct(int id) throws NoSuchElementException {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Product updateProduct(int id, Product product) {
        try {
            Product productToUpdate = this.getOneProduct(id);
            for (Product sameNameProduct : this.products) {
                if (product.getName().equals(sameNameProduct.getName())) {
                    return null;
                }
            }
            productToUpdate.setName(product.getName());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setPrice(product.getPrice());
            return productToUpdate;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }


    public Product deleteProduct(int id) {
        try {
            Product productToDelete = this.getOneProduct(id);
            this.products.remove(productToDelete);
            return productToDelete;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

}
