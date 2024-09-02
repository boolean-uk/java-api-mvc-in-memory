package com.booleanuk.api;

import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public ProductRepository(){
        products.add(new Product("Corn flakes", "Food", 20));
    }

    public void create(String name, String category, int price){
        for(Product p: products){
            if(p.getName().equals(name)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists in storage");
            }
        }
        Product product = new Product(name,category,price);
        this.products.add(product);
    }

    public List<Product> findAll(String category) {
        //If no parameter, just return the entire list
        if(category == null){
            return products;
        }
        List<Product> categoryList = new ArrayList<>();
        for(Product p: products){
            if(p.getCategory().equalsIgnoreCase(category)){
                categoryList.add(p);
            }
        }
        if(categoryList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find any products in the given category");
        }
        return categoryList;
    }

    public Product update(int id, Product newProduct){
        for(Product p: products){
            if(p.getId() == id){
                p.setName(newProduct.getName());
                p.setCategory(newProduct.getCategory());
                p.setPrice(newProduct.getPrice());
                return newProduct;
            }
        }
        return null;
    }

    public Product delete(int id){
        for(Product p: products){
            if(p.getId() == id){
                products.remove(p);
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product with the ID: " + id + " does not exist");
    }

    public Product findSpecificProduct(int id){
        for(Product p: products){
            if(p.getId() == id){
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find the product with ID: " + id);
    }

    public List<Product> getProducts() {
        return products;
    }
}
