package com.booleanuk.api.products.controllers;


import com.booleanuk.api.products.models.Product;
import com.booleanuk.api.products.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository products;
    // ResponseStatusException(HttpStatus.XXX, "message");
    public ProductController(ProductRepository repository) {
        this.products = repository;
    }

    public ProductController() {
        this.products = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProductsFiltered(@RequestParam(required = false, name = "category") String category) {
        if (category == null) {
            return this.products.getAll();
        }
        ArrayList<Product> matching = new ArrayList<>(this.products.getAll().stream().filter(product -> product.getCategory().equals(category)).toList());
        if (matching.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return matching;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        try {
            return findProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found.");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product) {
        boolean sameName = this.products.getAll()
                .stream()
                .anyMatch(filter -> filter.getName().equals(product.getName()));
        if (sameName) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists");
        }
        Product p = products.createProduct(product.getName(), product.getCategory(), product.getPrice());
        return p;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product putProduct(@PathVariable int id, @RequestBody Product product) {
        Product p;
        try {
            p = findProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found.");
        }
        boolean sameName = this.products.getAll()
                .stream()
                .anyMatch(filter -> filter.getName().equals(product.getName()));
        boolean sameProduct = (id == product.getId() && product.getName().equals(p.getName()));
        if (!sameProduct && sameName) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists");
        }
        p.setName(product.getName());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());
        return p;
    }

    @DeleteMapping("{id}")
    public Product removeProduct(@PathVariable int id) {
        Product p;
        try {
            p = findProduct(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Products not found.");
        }
        products.deleteProduct(p);
        return p;
    }

    private Product findProduct(int id) {
        return this.products.getAll().stream()
                .filter(product -> product.getId() == id)
                .findFirst().orElseThrow();
    }
}
