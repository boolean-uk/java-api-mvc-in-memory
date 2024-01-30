package com.booleanuk.api.bagels.controllers;


import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        return this.theProducts.getAll();
    }

    @GetMapping("{id}")

    public Product getOne(@PathVariable int id) {
        return this.theProducts.getOne(id);
    }

    @GetMapping("customcategory/{category}")
    public List<Product> getSpecific(@PathVariable String category) {
        return this.theProducts.getSpecific(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product created(@RequestBody Product product) {
        return this.theProducts.created(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateId(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.updateId(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteId(@PathVariable int id) {
        return this.theProducts.deleteId(id);
    }

}
