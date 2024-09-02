package com.booleanuk.api.extension;

import com.booleanuk.api.extension.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.extension.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Objects;

public class ProductRepository {

    private ArrayList<Product> products;

    ProductRepository() {
        this.products = new ArrayList<>();

    }

    public Product add(Product product) {
        for(Product value : products) {
            if (Objects.equals(value.getName(), product.getName())) {
                throw new ProductAlreadyExistsException("Product with the provided name already exists");
            }
        }
       products.add(product);
       return product;
    }

    public ArrayList<Product> getCategoryAll(String category) {
        if(category == null) {
            return products;
        }
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        if(filteredProducts.isEmpty()) {
            throw new ProductNotFoundException("No products of the provided category were found");
        } else return filteredProducts;

    }

    public Product getOne(int id) {
       for(Product product : products) {
           if (product.getId() == id) {
               return product;
           }
       }
        throw new ProductNotFoundException("Product not found");
    }

    public Product update(Product product, int id) {
        for (Product value : products) {
            if (Objects.equals(value.getName(), product.getName())) {
                throw new ProductAlreadyExistsException("Product with the provided name already exists");
            }
            if (value.getId() == id) {
                value.setName(product.getName());
                value.setCategory(product.getCategory());
                value.setPrice(product.getPrice());
                return value;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    public Product delete(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return products.remove(i);
            }
        }
        throw new ProductNotFoundException("Product not found");
    }


}
