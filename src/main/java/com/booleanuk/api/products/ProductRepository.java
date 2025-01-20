package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Bagel", "Food", 10));
        this.products.add(new Product("Tea", "Food", 12));
        this.products.add(new Product("Harry Potter", "Book", 100));
    }

    public List<Product> getProductsInCategory(String category) {
        List<Product> pCategory = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getCategory().toLowerCase().equals(category)) {
                pCategory.add(product);
            }
        }
        if (!pCategory.isEmpty()) {
            return pCategory;
        }
        return null;
    }

    public List<Product> getAll() {
        return this.products;
    }

    public Product create(Product product) {
        this.products.add(product);
        return product;
    }

    public Product getOne(int id) {
        for (Product product : this.products) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }

    public Product update(int id, Product product) {
        Product pToUpdate = getOne(id);
            if (pToUpdate != null) {
                this.products.get(id).setName(product.getName());
                this.products.get(id).setCategory(product.getCategory());
                this.products.get(id).setPrice(product.getPrice());
                return this.products.get(id);
            }
        return null;
    }

    public Product delete(int id) {
        Product pToDelete = getOne(id);
        if (pToDelete != null) {
            return this.products.remove(id);
        }
        return null;
    }

    public boolean isNameInList(Product product) {
        for (Product pToFind : this.products) {
            if (pToFind.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }
}
