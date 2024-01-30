package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(@RequestParam(name = "category", required = false) String category) {
        log.info("getAll (category='" + category + "')");

        if (category == null || category.isBlank()) {
            return ResponseEntity.ok(this.repository.findAll());
        }

        List<Product> returnList = this.repository.findAllByCategory(category);
        if (returnList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        return ResponseEntity.ok(returnList);
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        if (product.getName() == null || product.getCategory() == null) {
            log.info("add: Failed. Nulls in body: " + product);
            return ResponseEntity.badRequest().body(product);
        }

        if (this.repository.containsName(product.getName())) {
            log.info("add: Failed. Product with name '" + product.getName() + "' already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Other book by same name exists");
        }

        Product productToAdd = this.repository.create(product.getName(), product.getCategory(), product.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(productToAdd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(this.repository.find(id));
        } catch (NoSuchElementException e) {
            log.info("get: Failed. Item with id=[" + id + "] does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product newProduct) {
        // This should not return 400 Bad Request if the item is set to the same name as itself (e.g. we are only updating the price) -> fixed
        if (this.repository.containsName(newProduct.getName()) && !this.repository.find(id).getName().equals(newProduct.getName())) {
            log.info("update: Failed. Other item with name='" + newProduct.getName() + "' already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Other book by same name exists");
        }

        try {
            Product updatedProduct = this.repository.update(id, newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
        } catch (NoSuchElementException e) {
            log.info("update: Failed. Item with id=[" + id + "] does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable UUID id) {
        try {
            Product productToDelete = this.repository.delete(id);
            return ResponseEntity.ok(productToDelete);
        } catch (NoSuchElementException e) {
            log.info("delete: Failed. Item with id=[" + id + "] does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
    }
}
