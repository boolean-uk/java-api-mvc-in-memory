package com.booleanuk.api.bagels.controller;

import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> products = category == null ? this.productRepository.getAll() :
                this.productRepository.getAll().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase(category))
                        .collect(Collectors.toList());
        if (products.isEmpty() && category != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found in the specified category.");
        }
        return products;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid product details");
        }

        return this.productRepository.create(product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists"));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return this.productRepository.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return this.productRepository.update(id, product)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable int id) {
        return this.productRepository.delete(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
