package com.booleanuk.api.controllers;
import com.booleanuk.api.models.Product;
import com.booleanuk.api.models.repositories.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductsRepository repository;

    public ProductController() {
        this.repository = new ProductsRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> products = repository.getAll(category);
        if (products == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product of the provided category were found.");
        }
        return products;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOne(@PathVariable int id) {
        Product product;
        try {
            product = repository.getOne(id);
        } catch (NoSuchElementException e) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Product with that id was found.");
    }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        try {
            repository.create(product);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists.");
        }
        return product;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct;
        try {
            updatedProduct = this.repository.update(id, product);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists.");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Product with that id was found.");
        }
        return updatedProduct;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product remove(@PathVariable int id){
        Product deletedProduct;
        try {
            deletedProduct = repository.remove(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Product with that id was found.");
        }
        return deletedProduct;

    }



}
