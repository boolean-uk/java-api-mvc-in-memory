package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Locale;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product create(Product product){
        for(Product product1 : products){
            if(product.getName().equalsIgnoreCase(product1.getName())
                    && product.getCategory().equalsIgnoreCase(product1.getCategory())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product exists");
            }
        }
        this.products.add(product);
        return product;
    }
    public ArrayList<Product> getAll(String category){
        //Check for presenting the list
        if(category == null && products.size() != 0){
            return this.products;
        }else if(category == null && products.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The list is empty!");
        }

        //Copy over the products with matching categories
        ArrayList<Product> presentList = new ArrayList<>();
        for(Product product1 : products){
            if(category.equalsIgnoreCase(product1.getCategory())){
                presentList.add(product1);
            }
        }

        return presentList;
    }

    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product update(int id,Product product) {
        for(Product product1 : products){
            if (product1.getId() == id){
                product1.setId(id);
                if(product.getName().equals(product1.getName())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product already has that name!");
                }
                product1.setName(product.getName());
                product1.setCategory(product.getCategory());
                product1.setPrice(product.getPrice());
                return product1;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for(Product product : products){
            if (product.getId() == id){
                products.remove(product);
                return product;
            }
        }
        return null;
    }
}
