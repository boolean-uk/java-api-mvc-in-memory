package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        products.add(new Product("How to buil APIs", "Book", 1500));
    }

    public boolean addProduct(Product product){
        for(Product pr: products){
            if (pr.getName().equals(product.getName()))
                return false;
        }
        products.add(product);
        return true;
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findAll(String category) {
        List<Product> foundPr = new ArrayList<>();
        for (Product pr: products){
            if (pr.getCategory().equals(category))
                foundPr.add(pr);
        }
        return foundPr;
    }

    public Product find(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product delete(int id){
        for (Product product : this.products) {
            if (product.getId() == id) {
                this.products.remove(product);
                return product;
            }
        }
        return null;
    }

    public Product update(int id, Product product ){
        for (Product pr: products){
            if (pr.getName().equals(product.getName()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Product product1 = find(id);
        if (product1 != null){
            product1.setName(product.getName());
            product1.setCategory(product.getCategory());
            product1.setPrice(product.getPrice());
            return product1;
        }
        return null;
    }
}
