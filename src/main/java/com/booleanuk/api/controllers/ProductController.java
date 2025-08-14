package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = productRepository.find(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found!!!!"); // 404 basically
        }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (productRepository.getProducts().contains(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not cool.");
        }
        productRepository.create(product);
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable (name = "id") int id, @RequestBody Product product) {
        if (id < productRepository.getProducts().size()) {
            productRepository.getProducts().get(id).setType(product.getType());
            productRepository.getProducts().get(id).setCategory(product.getCategory());
            productRepository.getProducts().get(id).setPrice(product.getPrice());
            return productRepository.getProducts().get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        if (id < productRepository.getProducts().size()) {
            Product remove = productRepository.find(id);
            productRepository.updateId(id);
            return productRepository.getProducts().remove(remove.getId());
        }
        return null;
    }
}
