package com.booleanuk.api.bagels.controllers;

import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController(){
        repository = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return repository.findByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        repository.addProduct(product);
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product){
        if(repository.updateProduct(id, product))
            return product;
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        return repository.delete(id);
    }
}
