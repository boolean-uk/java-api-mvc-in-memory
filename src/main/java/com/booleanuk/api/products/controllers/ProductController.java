package com.booleanuk.api.products.controllers;

import com.booleanuk.api.products.models.Product;
import com.booleanuk.api.products.repositories.ProductRepository;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {
        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = this.theProducts.getOne(id);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer says no!");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if(product.getName() == null || product.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nulls in the body!");
        }
        return this.theProducts.create(product);

        //return this.theAuthors.getOne(newlyCreatedAuthor.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.update(id, product);
    }
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.theProducts.delete(id);
    }


}
