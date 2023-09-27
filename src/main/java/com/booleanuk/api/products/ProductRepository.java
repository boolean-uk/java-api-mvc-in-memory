package com.booleanuk.api.products;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductRepository {
    private static int idCounter = 4;
    private final List<Product> products;
    // TODO: Make this a set with a custom comparator - with ID

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1, "Apple","fruit",2));
        this.products.add(new Product(2, "Aubergine","vegetable",3));
        this.products.add(new Product(3, "Cake","dessert",10));
    }


    public Product create(String name, String category, int price) {
        Product product = new Product(idCounter++, name, category, price);
        this.products.add(product);
        return product;
    }

    public Product find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product update(int id, String name, String category, int price) {
        Product product = this.find(id);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public Product delete(int id) {
        Product product = this.find(id);
        this.products.remove(product);
        return product;
    }
}
