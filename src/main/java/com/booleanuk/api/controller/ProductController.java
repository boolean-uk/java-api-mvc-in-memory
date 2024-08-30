package com.booleanuk.api.controller;

import com.booleanuk.api.model.Product;
import com.booleanuk.api.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepository) {
        this.productRepo = productRepository;

        // Dummy data.
        this.productRepo.create("PC", "Electronic", 2000);
        this.productRepo.create("PS5", "Electronic", 3500);
        this.productRepo.create("Table", "Furniture", 1500);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllOrByCategory(@RequestParam(required = false) String category) {
        boolean exists = this.productRepo.doesCategoryExist(category);
        if (category != null && !exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product of the provided category were found");
        }
        if (category != null) {
            return this.productRepo.findAll(category.toLowerCase());
        }
        return this.productRepo.findAll(null);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        boolean success = this.productRepo.create(
                product.getName(),
                product.getCategory(),
                product.getPrice()
        );

        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable int id) {
        Product product = this.productRepo.findById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product productWithId = new Product(
                id,
                product.getName(),
                product.getCategory(),
                product.getPrice()
        );
        this.productRepo.updateProduct(productWithId);
        return product;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable int id) {
        boolean success = this.productRepo.deleteProduct(id);
        if (success) {
            return "Product successfully deleted!";
        }
        return "Failed to delete product...";
    }
}
