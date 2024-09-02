package com.booleanuk.api.core.product;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    ProductController(){
        productRepository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productRepository.add(product);

    }

    @GetMapping
    public ArrayList<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @GetMapping("{id}")
    public Product getOneProduct(@PathVariable int id) {
        return productRepository.getOne(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productRepository.update(product, id);
    }

    @DeleteMapping("{id}")
    public Product deleteAuthor(@PathVariable int id) {
        return productRepository.delete(id);
    }

}
