package com.booleanuk.api.requests.repositories;

import com.booleanuk.api.requests.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }

    public String addProduct(Product product) {
        if (doesNameExist(product)) {
            return "exists";
        }
        boolean created = this.productList.add(product);
        if (created) {
            return "created";
        }
        return "error";
    }

    public List<Product> getAllProducts() {
        return this.productList;
    }

    public List<Product> getProductsWithCategory(String category) {
        List<Product> categorisedProducts = new ArrayList<>();
        for (Product product : this.productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                categorisedProducts.add(product);
            }
        }
        return categorisedProducts;
    }

    public Product getSpecificProduct(int id) {
        for (Product product : this.productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public String changeProductInfo(int id, Product product) {
        if (doesNameExist(product)) {
            return "exists";
        }
        for (Product productToChange : this.productList) {
            if (productToChange.getId() == id) {
                productToChange.setName(product.getName());
                productToChange.setCategory(product.getCategory());
                productToChange.setPrice(product.getPrice());
                return "changed";
            }
        }
        return "error";
    }

    public Product removeProduct(int id) {
        for (Product productToDelete : this.productList) {
            if (productToDelete.getId() == id) {
                this.productList.remove(productToDelete);
                return productToDelete;
            }
        }
        return null;
    }

    private boolean doesNameExist(Product product) {
        for (Product productSearch : this.productList) {
            if (productSearch.getName().equalsIgnoreCase(product.getName())) {
                return true;
            }
        }
        return false;
    }
}
