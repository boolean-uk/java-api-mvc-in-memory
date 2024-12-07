package com.booleanuk.api.products.controllers;
import com.booleanuk.api.products.model.Product;
import com.booleanuk.api.products.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }
    @GetMapping
    public List<Product> getAll() {
        return this.repository.findAll();
    }
    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = this.repository.find(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product with this id");
        }
        return product;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if (product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can't be null, price can't be 0");
        }
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return  this.repository.update(id, product);
    }
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.repository.delete(id);
    }
}
