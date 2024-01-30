package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;
    
    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product("How to build APIs", "Book", 1500));
        this.products.add(new Product("Onion bagel", "Food", 20));
    }

    public List<Product> getAll(String category) {
        if(category == null) {
            return products;
        }
        ArrayList<Product> productsOfCategory = new ArrayList<>();

        for(Product product: products) {
            if(product.getCategory().equalsIgnoreCase(category)) {
                productsOfCategory.add(product);
            }
        }
        return productsOfCategory;
    }

    public Product getOne(int id) {
        for(Product product: products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(Product product) {
        products.add(product);
        return product;
    }

    public Product update(int id, Product product) {
        for(Product currentProduct: products) {
            if(currentProduct.getId() == id) {
                currentProduct.setName(product.getName());
                currentProduct.setCategory(product.getCategory());
                currentProduct.setPrice(product.getPrice());
                return currentProduct;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return products.remove(i);
            }
        }
        return null;
    }

    public Product getProductWithName(String name) {
        for(Product product: products) {
            if(product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
