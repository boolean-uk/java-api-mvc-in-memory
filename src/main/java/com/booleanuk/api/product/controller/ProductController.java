package com.booleanuk.api.product.controller;

import com.booleanuk.api.product.model.Product;
import com.booleanuk.api.product.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        productRepository = new ProductRepository();
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAll(@RequestParam("category") Optional<String> category) {
        if(category.isPresent()) {
            if (productRepository.getAll() != null) {
                List<Product> productsCategory = new ArrayList<>();
                for (int i = 0; i < productRepository.getAll().size(); i++) {
                    if (productRepository.getAll().get(i).getCategory().equals(category.get())) {
                        productsCategory.add(productRepository.getAll().get(i));
                    }
                }
                if(productsCategory.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No products of the provided category were found");
                }
                return productsCategory;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No products of the provided category were found");
        }
        if (productRepository.getAll() != null) {
            return productRepository.getAll();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found");
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        if(productRepository.getOne(id) != null) {
            return productRepository.getOne(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        if (productRepository.create(product) != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        if (productRepository.update(id,product) != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        if (productRepository.delete(id) != null) {
            return productRepository.getOne(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
    }
}
