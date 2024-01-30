package com.booleanuk.api.bagels;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> products;


    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product(idCounter++, "Java", "Book", 1000));
        this.products.add(new Product(idCounter++, "Onion Bagel", "Food", 1000));
    }


}
