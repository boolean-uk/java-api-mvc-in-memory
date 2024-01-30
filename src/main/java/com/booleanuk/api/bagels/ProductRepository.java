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


}
