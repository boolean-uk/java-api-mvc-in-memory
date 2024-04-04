package com.booleanuk.api.requests.repositories;

import com.booleanuk.api.requests.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
    }

    public Product createProduct(String name, String category, int price) {
        Product product = new Product(name, category, price, idCounter++);
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
                .orElse(null);
    }

    public List<Product> findAllOfSpecificCategory(String category) {
        List<Product> productsOfSpecificCategory = new ArrayList<>();
        for(Product product : this.products) {
            if(product.getCategory().equalsIgnoreCase(category)) {
                productsOfSpecificCategory.add(product);
            }
        }
        return productsOfSpecificCategory;
    }

    public Product updateProduct(int id, String name, String category, int price) {
        Product updatedProduct = null;

        for(Product product : this.products) {
            if(product.getId() == id) {
                updatedProduct = product;
                product.setName(name);
                product.setCategory(category);
                product.setPrice(price);
            }
        }
        return updatedProduct;
    }

    public Product deleteProduct(int id) {
        Product deletedProduct = null;

        for(Product product : this.products) {
            if(product.getId() == id) {
                this.products.remove(product);
            }
        }
        return deletedProduct;
    }
}
