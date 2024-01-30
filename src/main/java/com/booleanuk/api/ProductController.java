package com.booleanuk.api;

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
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> returnedList = this.theProducts.getAll(category);
        if (returnedList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return returnedList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOne(@PathVariable int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (this.theProducts.getAll(null).stream().anyMatch(p -> p.getName().equals(product.getName()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create product, some filed is null.");
        }
        return this.theProducts.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        if (this.theProducts.getAll(null).stream().anyMatch(p -> p.getName().equals(product.getName()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not update product, some filed is null.");
        }
        Product returnedProduct = this.theProducts.update(id, product);
        if (returnedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return returnedProduct;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable int id) {
        Product returnedProduct = this.theProducts.delete(id);
        if (returnedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return returnedProduct;
    }
}