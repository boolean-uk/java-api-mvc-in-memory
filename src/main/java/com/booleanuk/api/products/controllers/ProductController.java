package com.booleanuk.api.products.controllers;

import com.booleanuk.api.products.models.Product;
import com.booleanuk.api.products.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(){
        this.productRepository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return this.productRepository.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return this.productRepository.update(id, product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return this.productRepository.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return this.productRepository.get(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        return this.productRepository.delete(id);
    }


}
