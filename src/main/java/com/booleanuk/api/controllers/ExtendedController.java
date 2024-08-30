package com.booleanuk.api.controllers;


import com.booleanuk.api.product.Product;
import com.booleanuk.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("extend")
public class ExtendedController {

    private ProductRepository ourProducts;

    public ExtendedController(){
        this.ourProducts=new ProductRepository();
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){


        if(ourProducts.getProduct(product.getId())!=null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists.");
        }
        ourProducts.add(product);
        return product;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Product> getCategory(@RequestParam String category){
        ArrayList<Product> catProd=new ArrayList<>();
        for(Product p: ourProducts.getProducts()){
            if (p.getCategory()==category){
                catProd.add(p);

            }
        }
        if (!catProd.isEmpty()){
            return catProd;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products within this category.");
        }
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id){

        for(Product p: ourProducts.getProducts()){
            if(p.getId()==id){
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not available.");
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product changeProduct(@RequestBody Product product, @PathVariable int id){
        int pos=0;
        boolean found=false;
        for(int i=0; i<ourProducts.getProducts().size();i++){
            if(ourProducts.getProducts().get(i).getName()==product.getName()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is taken.");
            }

            if(ourProducts.getProducts().get(i).getId()==id){
                pos=i;
                found=true;
            }
        }


        if(found){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        ourProducts.getProducts().get(pos).setName(product.getName());
        ourProducts.getProducts().get(pos).setCategory(product.getCategory());
        ourProducts.getProducts().get(pos).setPrice(product.getPrice());

        return ourProducts.getProducts().get(pos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id){

        for(Product p: ourProducts.getProducts()){
            if(p.getId()==id){
                return ourProducts.remove(id);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }








}