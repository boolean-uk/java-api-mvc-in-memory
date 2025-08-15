package com.booleanuk.api.products.model;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product("product1", "category1", 100));
        this.products.add(new Product("product2", "category2", 40));
        this.products.add(new Product("product3", "category3", 90));

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

    public Product addProduct(Product newProduct) {
        for (Product product : this.products) {
            if (product.getName().equals(newProduct.getName())) {
                return null;
            }
        }
        products.add(newProduct);
        return newProduct;
    }

    public boolean checkName(Product product, int id){
        for (Product productInList : this.products) {
            if (productInList.getName().equals(product.getName())) {
                return false;
            }
        } return true;
    }

    public Product deleteProduct(int id){
        for (Product product : this.products) {
            if (product.getId() == id) {
                products.remove(product);
                return product;
            }
        }
        return null;
    }

    public void updateProduct(int id, Product product) {
        for (Product productInList : this.products) {
            if (productInList.getId() == id) {
                productInList.setName(product.getName());
                productInList.setCategory(product.getCategory());
                productInList.setPrice(product.getPrice());
            }
        }
    }
}



