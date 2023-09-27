package com.booleanuk.api.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("{id}")
    public Product findOneProduct(@PathVariable int id) {
        return this.repository.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.repository.update(id, product.getName(), product.getCategory(), product.getPrice());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product deleteProduct(@PathVariable int id) {
        return this.repository.delete(id);
    }
}
