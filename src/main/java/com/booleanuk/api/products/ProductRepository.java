package com.booleanuk.api.products;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products = new ArrayList<>(){{
        add(new Product("How to build APIs", "Book", 1500));
        add(new Product("Elden Ring", "Game", 90));
    }};

    public Product createProduct(Product product){
        for (Product p : products){
            if (p.getName().equals(product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided name already exists!");
            }
        }
        this.products.add(product);
        return product;
    }

    public ArrayList<Product> getAll(){
        return this.products;
    }

    public ArrayList<Product> getByCategory(String category){
        ArrayList<Product> categoryList = new ArrayList<>();
        for (Product p : products){
            if (p.getCategory().toLowerCase().equals(category)){
                categoryList.add(p);
            }
        }
        if (!categoryList.isEmpty()){
            return categoryList;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found!");
    }

    public Product getOne(int id){
        for (Product p : products){
            if (p.getId() == id){
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    public Product updateProduct(int id, Product product){
        for (Product p : products){
            if (p.getId() == id){
                for (Product ps : products){
                    if (ps.getName().equals(product.getName()) && ps != p){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists!");
                    }
                }
                p.setName(product.getName());
                p.setCategory(product.getCategory());
                p.setPrice(product.getPrice());
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

    public Product deleteProduct(int id){
        for (Product p : products){
            if (p.getId() == id){
                products.remove(p);
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
    }

}
