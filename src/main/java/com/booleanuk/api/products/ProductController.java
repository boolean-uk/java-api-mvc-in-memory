package com.booleanuk.api.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.util.StringUtils.hasLength;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

//    public ProductController() {
//        this.productRepository = new ProductRepository();
//    }

    @GetMapping
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable int id) {
        try {
            return this.productRepository.find(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find the product!");
        }
//        catch (Exception e) {
//            // do something else
//            // this is the way to check about multiple different exceptions
//        } finally {
//          ...
//          }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (!hasLength(product.getName()) || !hasLength(product.getCategory()) || product.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, category and a positive price are required.");
        }
        return this.productRepository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        if (!hasLength(product.getName()) || !hasLength(product.getCategory()) || product.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, category and a positive price are required.");
        }
        try {
            return this.productRepository.update(id, product.getName(), product.getCategory(), product.getPrice());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find the product!");
        }
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        try {
            return this.productRepository.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find the product!");
        }
    }
}
