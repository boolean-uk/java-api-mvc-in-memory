package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("Coffee", "Drinks", 3));
        products.add(new Product("Tea", "Drinks", 2));
        products.add(new Product("Bagel", "Food", 2));
    }

    public List<Product> getAll() {
        return this.products;
    }

    public Product create(Product product) {
        this.products.add(product);
        return product;
    }

    public Product findById(int id) {
        for(Product product: products) {
            if(product.getId() == id)
                return product;
        }
        return null;
    }

    public Product update(int id, Product product) {
        for(Product item: products) {
            if(item.getId() == id) {
                item.setName(product.getName());
                item.setCategory(product.getCategory());
                item.setPrice(product.getPrice());
                return item;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for(Product product: products) {
            if(product.getId() == id) {
                this.products.remove(product);
                return product;
            }
        }
        return null;
    }
}
