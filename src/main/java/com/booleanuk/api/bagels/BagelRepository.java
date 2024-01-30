/*
package com.booleanuk.api.bagels;

import com.booleanuk.api.requests.Product;

import java.util.ArrayList;
import java.util.List;

public class BagelRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public void create(String type, int price) {
        Product product = new Product(this.idCounter++, type, price);
        this.data.add(product);
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product delete(int id) {
        for (Product product : data) {
            if (product.getId() == id) {
                data.remove(product);
                return product;
            }
        }
        return null;
    }


}
*/
