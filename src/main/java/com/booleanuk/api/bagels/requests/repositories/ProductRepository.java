package com.booleanuk.api.bagels.requests.repositories;

import com.booleanuk.api.bagels.requests.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Monster", "Energy", 15));
        this.products.add(new Product("Booster", "Energy", 30));
        this.products.add(new Product("Banana", "Fruit", 3));
        this.products.add(new Product("Mango", "Fruit", 20));
        this.products.add(new Product("Macbook", "Electronics", 15000));
    }

    public Product getOne(int id) {
        for (Product p : this.products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Product> getAll() {
        return this.products;
    }

    public Product deleteOne(int id) {
        for (Product p : this.products) {
            if (p.getId() == id) {
                this.products.remove(p);
                return p;
            }
        }
        return null;
    }

    public Product createOne(Product p) {
        this.products.add(p);
        return p;
    }

    public Product updateOne(int id, Product product) {
        for(Product p : this.products) {
            if (p.getId() == id) {
                p.setName(product.getName());
                p.setCategory(product.getCategory());
                p.setPrice((product.getPrice()));
                return p;
            }
        }
        return null;
    }
}
