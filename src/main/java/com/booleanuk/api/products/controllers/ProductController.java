package com.booleanuk.api.products.controllers;

import com.booleanuk.api.exceptions.NoProductFound;
import com.booleanuk.api.exceptions.ProductNameAlreadyExist;
import com.booleanuk.api.products.models.Product;
import com.booleanuk.api.products.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        try {
            return this.repository.create(product);
        } catch (ProductNameAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String category) {
        try {
            return this.repository.getAll(category);
        } catch (NoProductFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public Product getSpecificProduct(@PathVariable int id) {
        try {
            return this.repository.getSpecific(id);
        } catch (NoProductFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        try {
            return this.repository.update(id, product);
        } catch (NoProductFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ProductNameAlreadyExist e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public Product delete(@PathVariable int id) {
        try {
            return this.repository.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
