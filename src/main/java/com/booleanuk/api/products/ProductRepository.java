package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private int idProduct = 1;
    private List<Product> products = new ArrayList<>();

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idProduct++, name, category, price);
        this.products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product update(int id, String name, String category, int price) {
        for (Product prd : products) {
            if (prd.getId() == id) {
                prd.setName(name);
                prd.setCategory(category);
                prd.setPrice(price);
                return prd;
            }
        }
        return null;
    }

    public Product delete(int id) {
        Product product = null;

        for (Product prd : products) {
            if (prd.getId() == id) {
                product = prd;
            }
        }

        if (product != null) {
            this.products.remove(product);
        }
        return null;
    }
}
