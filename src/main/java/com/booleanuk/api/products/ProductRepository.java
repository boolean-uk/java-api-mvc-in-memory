package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("name", "category", 100));
        this.products.add(new Product("name2", "category2", 100));
        this.products.add(new Product("name3", "category3", 100));
    }

    public Product create(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product exitingProducts = products.get(i);
            if (Objects.equals(exitingProducts.getName(), product.getName())) {
                return null;
            }
        }
        this.products.add(product);
        return product;
    }

    public ArrayList<Product> read() {
        return this.products;
    }

    public ArrayList<Product> read(String category) {
        return (ArrayList<Product>) this.products.stream().filter(product -> product.getCategory().toLowerCase() == category.toLowerCase()).collect(Collectors.toList());
    }


    public Product read(int id) {
        return this.products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public Product update(int id, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == id) {
                products.get(i).setName(newProduct.getName());
                products.get(i).setCategory(newProduct.getCategory());
                products.get(i).setPrice(newProduct.getPrice());
                return products.get(i);
            }
        }
        return null;
    }

    public Product delete(int id) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId() == id) {
                return products.remove(i);
            }
        }
        return null;
    }
}
