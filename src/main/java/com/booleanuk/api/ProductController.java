package com.booleanuk.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository repo = new ProductRepository();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        Product created = repo.create(product);
        if (created == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        return created;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String category) {
        List<Product> result = new ArrayList<>();
        if (category != null) {
            result = repo.getCategory(category);
            if (result == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No products of the provided category were found.");
            }
        } else {
            result = repo.getAll();
        }
        return result;
    }

    @GetMapping("/{id}")
    public Product getSpecificProduct(@PathVariable int id) {
        Product product = repo.getProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product updated) {
        Product product = repo.update(id, updated);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product product = repo.delete(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product;
    }

}
