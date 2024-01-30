package com.booleanuk.api.products;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;
    
    public ProductController() {
        this.theProducts = new ProductRepository();
    }
    
    @GetMapping
    public List<Product> getAll() {
        return theProducts.getAll();
    }
    
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        return theProducts.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.theProducts.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        return this.theProducts.delete(id);
    }
}