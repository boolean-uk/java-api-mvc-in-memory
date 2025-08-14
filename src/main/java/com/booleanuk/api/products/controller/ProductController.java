package com.booleanuk.api.products.controller;

import com.booleanuk.api.products.model.Product;
import com.booleanuk.api.products.model.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        return this.repository.findAll(category);
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id) {
        return repository.find(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct){
        return repository.addProduct(newProduct);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody String name){
        return repository.updateProduct(id, name);
    }

    @DeleteMapping("{id}")
    public boolean deleteProduct(@PathVariable int id){
        return repository.deleteProduct(id);
    }
}
