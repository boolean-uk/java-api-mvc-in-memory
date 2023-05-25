package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepo;

    public ProductController() {
        this.productRepo = new ProductRepository();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productRepo.create(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ArrayList<Product> readAll() {
        return productRepo.read();
    }

    @GetMapping("/{id}")
    public Product readOne(@PathVariable Integer id) {
        Product readProduct = productRepo.read(id);
        if (readProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return readProduct;
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        Product updatedProduct = productRepo.update(id, product);
        if (updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Integer id) {
        Product deletedProduct = productRepo.delete(id);
        if (deletedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return deletedProduct;
    }

}
