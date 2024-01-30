package com.booleanuk.api.requests.controllers;

import com.booleanuk.api.requests.models.Product;
import com.booleanuk.api.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        Product createdProduct = this.repository.create(product.getName(), product.getCategory(), product.getPrice());
        if (createdProduct!= null){
            return createdProduct;
        }
        return  null;
    }

    @GetMapping
    List<Product> getAll(){
        return this.repository.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        return this.repository.getOne(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product){
        if (this.repository.update(id, product) != null){
            return this.repository.getOne(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        return this.repository.delete(id);
    }



}
