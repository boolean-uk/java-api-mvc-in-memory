package com.booleanuk.api.bagels.model;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    List<Product> products = new ArrayList<>();

    public List<Product> getAll() {
        if(products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.products;
    }

    public Product postOne(Product product) {
        if(this.products.contains(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        this.products.add(product);
        return product;
    }

    public Product getOne(int id) {
        for(Product product : this.products) {
            if(product.getId() == id) {
                return product;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Product putOne(int id, Product product) {

        for(Product prod : this.products) {
            if(prod.getName().equalsIgnoreCase(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(prod.getId() == id) {
                prod.setName(product.getName());
                prod.setCategory(product.getCategory());
                prod.setPrice(product.getPrice());
                return prod;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Product deleteOne(int id) {
        for(Product prod : this.products) {
            if(prod.getId() == id) {
                this.products.remove(prod);
                return prod;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
