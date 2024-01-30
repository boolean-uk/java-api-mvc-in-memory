package com.booleanuk.api.bagels.requests.controllers;


import com.booleanuk.api.bagels.requests.models.Product;
import com.booleanuk.api.bagels.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOne(@PathVariable int id) {
        return theProducts.getOne(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteOne(@PathVariable int id) {
        return theProducts.deleteOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createOne(@RequestBody Product author) {
        return theProducts.createOne(author);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateOne(@PathVariable int id, @RequestBody Product author) {
        return theProducts.updateOne(id, author);
    }
}
