package com.booleanuk.api.products.controller;

import com.booleanuk.api.products.controller.dto.ProductDto;
import com.booleanuk.api.products.model.pojo.Product;
import com.booleanuk.api.products.model.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    // Get by category
    @GetMapping(params = "category")
    public ResponseEntity<?> getByCategory(@RequestParam String category) {
        try {
            return ResponseEntity.ok(this.productRepository.findAllByCategory(category));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(productRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Create
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productCreateDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.create(new Product(productCreateDto)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // Update by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductId(@PathVariable int id, @RequestBody ProductDto body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.productRepository.updateById(id, body));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            return ResponseEntity.ok(productRepository.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }
}
