package com.booleanuk.api.products;

import com.booleanuk.api.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository productRepository = new ProductRepository();

    @GetMapping()
    public HashMap<Integer, Product> getAll(@RequestParam(required = false) String category) {
        return this.productRepository.getAll(category);
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable int id) {
        return this.productRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.productRepository.create(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.productRepository.update(id, product);
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.productRepository.delete(id);
    }
}
