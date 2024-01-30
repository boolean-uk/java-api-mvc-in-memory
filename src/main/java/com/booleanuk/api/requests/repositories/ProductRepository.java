package com.booleanuk.api.requests.repositories;

import com.booleanuk.api.requests.models.Product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getAllProducts() {
        return this.products;
    }

    public Product getOneProduct(int id) {
        for (Product author : this.products) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    public Product createProduct(Product product) {
        this.products.add(product);
        return product;
    }

    public Product updateProduct(int id, Product theProduct) {
        for (Product product : this.products) {
            if(product.getId() == id) {
                product.setName(theProduct.getName());
                product.setCategory(theProduct.getCategory());
                product.setPrice(theProduct.getPrice());
                return product;
            }
        }
        return null;
    }

    public Product deleteProduct(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                this.products.remove(product);
                return product;
            }
        }
        return null;
    }
}
