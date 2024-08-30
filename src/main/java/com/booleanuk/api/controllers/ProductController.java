package com.booleanuk.api.controllers;


import com.booleanuk.api.product.Product;
import com.booleanuk.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {


    private ProductRepository ourProducts;

    public ProductController(){
        this.ourProducts=new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        ourProducts.add(product);

        return product;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Product> getAll(){
        return ourProducts.getProducts();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id){

        for(Product p: ourProducts.getProducts()){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product changeProduct(@RequestBody Product product, @PathVariable int id){

        for(Product p: ourProducts.getProducts()){
            if(p.getId()==id){
                p.setName(product.getName());
                p.setCategory(product.getCategory());
                p.setPrice(product.getPrice());
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id){

        for(Product p: ourProducts.getProducts()){
            if(p.getId()==id){
                return ourProducts.remove(id);
            }
        }
        return null;
    }






}
