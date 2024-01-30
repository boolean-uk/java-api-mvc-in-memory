package com.booleanuk.api.bagels;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;


    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Java", "Book", 1000));
        this.products.add(new Product("Onion Bagel", "Food", 1000));
    }

    public Product create(Product product) {
        if (this.products.stream().noneMatch(p->p.getName().equals(product.getName()))) {
            this.products.add(product);
            return product;
        }
        return null;
    }
}
