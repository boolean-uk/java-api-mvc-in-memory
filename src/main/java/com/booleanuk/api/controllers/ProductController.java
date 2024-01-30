package com.booleanuk.api.controllers;

import java.util.List;
import java.util.Optional;

import com.booleanuk.api.repositories.ProductRepository;
import com.booleanuk.api.requests.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(name = "category", required = false) String category) {
        if (category == null || category.isBlank()) {
            return this.repository.findAll();
        } else {
            return this.repository.findByCategory(category);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@RequestBody Product productRequest) {
        if (productRequest.getName() == null || productRequest.getType() == null) {
            String errorMessage = "Failed to create product. Name and type cannot be null.";
            log.info("create: " + errorMessage + " Product: " + productRequest);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        // Check if a product with the same name already exists
        if (repository.productWithNameExists(productRequest.getName())) {
            String errorMessage = "A product with this name already exists.";
            log.info("create: " + errorMessage + " Product: " + productRequest);
            throw new ResponseStatusException(HttpStatus.CONFLICT, errorMessage);
        }

        Product productToAdd = this.repository.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productToAdd);
    }

    @GetMapping("/{id}")
    public Product getSpecificProduct(@PathVariable int id) {
        Optional<Product> optionalProduct = this.repository.find(id);
        Product product = optionalProduct.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "A product with this id does not exist"));

        return product;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product modifyProductById(@PathVariable int id, @RequestBody Product modifiedProduct) {
        return this.repository.modify(id, modifiedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Optional<Product> optionalProductToDelete = this.repository.find(id);

        if (optionalProductToDelete.isPresent()) {
            Product deletedProduct = this.repository.delete(id);
            return ResponseEntity.ok("Product with ID " + id + " has been deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found");
        }
    }
}
