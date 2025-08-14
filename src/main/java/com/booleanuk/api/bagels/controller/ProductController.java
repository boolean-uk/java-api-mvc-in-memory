package com.booleanuk.api.bagels.controller;

import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repositories.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        productRepository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {
        List<Product> products =  this.productRepository.findAll();

        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not products were found");
        }

        return products;
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable int id) {
        Product product =  this.productRepository.getOne(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found");
        }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {

        if (productRepository.checkName(product.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not created");
        }

        this.productRepository.addProduct(product);
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product) {

        if (productRepository.checkName(product.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product exists already");
        }
        else if (id < this.productRepository.size()) {
            this.productRepository.findAll().get(id).setId(id);
            this.productRepository.findAll().get(id).setName(product.getName());
            this.productRepository.findAll().get(id).setCategory(product.getCategory());
            this.productRepository.findAll().get(id).setPrice(product.getPrice());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        if (id < this.productRepository.size()) {
            return this.productRepository.findAll().remove(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        }
    }
}
