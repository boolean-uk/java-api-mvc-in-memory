package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll() {
        return this.productRepository.getAll();
    }

    @PostMapping("/auto")
    public ResponseEntity<Product> createAutoProduct() {
        Product autoProduct = new Product("Gaming Chair", "Furniture", 199.99);
        Product savedProduct = productRepository.addProduct(autoProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean deleted = productRepository.delete(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Product with ID " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found.");
        }
    }



}
