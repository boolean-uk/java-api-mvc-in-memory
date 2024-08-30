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
    ProductRepository productRepository;

    public ProductController() {
        productRepository = new ProductRepository();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Product> getAll(@RequestParam (required = false) String type) {
        if(type == null){
            return this.productRepository.getAll();
        }else {
            return this.productRepository.getAllTypeOfProducts(type);
        }

    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id) {
        Product product = productRepository.getById(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return this.productRepository.getById(id);
    }

    @DeleteMapping("{id}")
    public Product deleteById(@PathVariable int id) {
        Product productOld = this.productRepository.getById(id);
        if(productOld == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return this.productRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product productOld =this.productRepository.getById(id);
        if(productOld == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        for (Product productChecker: this.productRepository.getAll()) {
            if(productChecker.getName().equals(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
            }
        }
        return this.productRepository.updatePublisher(id, product);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        if(product.getName().isEmpty() || product.getName() == null && product.getPrice() < 0 && product.getType().isEmpty() || product.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot be created with null values");
        }
        for (Product productChecker: this.productRepository.getAll()) {
            if(productChecker.getName().equals(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
            }
        }
        return this.productRepository.createProduct(product);
    }

}
