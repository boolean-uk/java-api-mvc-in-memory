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

    public List<Product> getAll() {
        return this.products;
    }

    public List<Product> getAll(String category) {
        return this.products.stream().filter(p->p.getCategory().equalsIgnoreCase(category)).toList();
    }

    public Product getSpecific(int id) {
        return this.products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Product delete(int id) {
        Product product = this.products.stream().filter(p->p.getId() == id).findFirst().orElse(null);
        if (product != null) {
            this.products.remove(product);
        }
        return product;
    }

    public Product update(int id, Product updateProduct) {
        Product product = this.products.stream().filter(p->p.getId() == id).findFirst().orElse(null);
        if (product != null) {
            product.setName(updateProduct.getName());
            product.setCategory(updateProduct.getCategory());
            product.setPrice(updateProduct.getPrice());
        }
        return product;
    }
}
