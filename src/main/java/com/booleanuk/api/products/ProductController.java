package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository products;

    public ProductController() {
        this.products = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.products.create(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return this.products.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return products.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable int id) {
        return products.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        return products.delete(id);
    }
}
