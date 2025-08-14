package com.booleanuk.api.products;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Laptop", "Electronics", 899.99));
        this.products.add(new Product("T-shirt", "Clothing", 19.99));
        this.products.add(new Product("Coffee Mug", "Kitchen", 5.49));
    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(Product product) {
        this.products.add(product);
        return product;
    }

    public Product update(int id, Product updatedProduct) {
        Product existingProduct = getOne(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        return existingProduct;
    }

    public boolean delete(int id) {
        Product existingProduct = getOne(id);
        if (existingProduct == null) {
            return false;
        }
        this.products.remove(existingProduct);
        return true;
    }

    public Product addProduct(Product product) {
        this.products.add(product);
        return product;
    }
}
