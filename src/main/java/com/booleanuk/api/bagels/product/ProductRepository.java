package com.booleanuk.api.bagels.product;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getAll(String category) {
        if(category == null) {
            return this.products;
        }
        ArrayList<Product> newList = new ArrayList<>();
        for(Product p : this.products) {
            if(p.getCategory().equalsIgnoreCase(category)) {
                newList.add(p);
            }
        }
        return newList;
    }

    public Product getOne(int id) {
        for(Product product : this.products) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(Product product) {
        for(Product p : this.products) {
            if(p.getName().equalsIgnoreCase(product.getName())) {
                return null;
            }
        }
        this.products.add(product);
        return product;
    }

    public Product update(int id, Product product) {

        Product theProduct = this.getOne(id);
        if(theProduct == null) {
            return theProduct;
        }
        theProduct.setName(product.getName());
        theProduct.setCategory(product.getCategory());
        theProduct.setPrice(product.getPrice());
        return theProduct;
    }

    public boolean checkIfNameIsUnique(Product product) {
        for(Product p : this.products) {
            if(p.getName().equalsIgnoreCase(product.getName())) {
                return false;
            }
        }
        return true;
    }

    public Product delete(int id) {
        Product theProduct = this.getOne(id);
        if(theProduct == null) {
            return theProduct;
        }
        this.products.remove(theProduct);
        return theProduct;
    }
}
