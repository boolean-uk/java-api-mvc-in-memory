package com.booleanuk.api.products.controller;

import com.booleanuk.api.products.model.Product;
import com.booleanuk.api.products.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll() {
        ArrayList<Product> products = productRepository.getAll();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found");
        }
        return products;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product newProduct){
        Product product = productRepository.addProduct(newProduct);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product is already in the list");
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = productRepository.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find this product");
        }
        return product;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Product product ) {
        Product productCheck = productRepository.getOne(id);
        if (productCheck == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find this product");
        }

        boolean updatedProduct = productRepository.checkName(product, id);

        if (!updatedProduct){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product is already in the list");
        }

        productRepository.updateProduct(id, product);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        Product product = productRepository.deleteProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find this product");
        }
        return product;
    }
}
