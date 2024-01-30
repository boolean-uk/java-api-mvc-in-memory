package com.booleanuk.api.bagels.controller;


import com.booleanuk.api.bagels.model.Product;
import com.booleanuk.api.bagels.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository = new ProductRepository();


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product postOne(@RequestBody Product product) {
        return this.productRepository.postOne(product);

    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Product> getProducts() {
        return this.productRepository.getAll();
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Product> getProducts(@RequestParam String category) {
        return this.productRepository.getAll(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Product getOne(@PathVariable (name = "id") int id) {
        return this.productRepository.getOne(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Product putOne(@PathVariable (name = "id") int id, @RequestBody Product product) {
        return this.productRepository.putOne(id, product);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Product deleteOne(@PathVariable (name = "id") int id) {
        return this.productRepository.deleteOne(id);
    }

}
