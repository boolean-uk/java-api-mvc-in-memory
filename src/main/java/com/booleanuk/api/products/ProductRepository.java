package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public void create(String name, String type, int price) {
        Product product = new Product(this.idCounter++, name, type, price);
        this.data.add(product);
    }

    public  List<Product> findAll() { return this.data; }
    public  List<Product> findAll(String category) {
        Stream<Product> productStream = data.stream() // Should learn to use stream() properly
                .filter(product -> product.getCategory().equalsIgnoreCase(category));
        return productStream.collect(Collectors.toList());
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product remove(int id) {
        Product product = find(id);
        if (data.contains(product)) {
            data.remove(product);
            return product;
        }
        return null;
    }

    public Product update(int id, Product updatedProduct) {
        Product product = find(id);
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        return product;
    }

    public boolean nameInData(String name) {
         return data.stream() // Should learn to use stream() properly
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    public boolean categoryInData(String category) {
        return data.stream() // Should learn to use stream() properly
                .anyMatch(product -> product.getCategory().equalsIgnoreCase(category));
    }

    public boolean idInData(int id) {
        return data.stream()
                .anyMatch(product -> product.getId() == id);
    }
}
