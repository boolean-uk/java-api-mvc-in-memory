package com.booleanuk.api.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (id > this.repository.findAll().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find this product");
        }
        return this.repository.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product is null");
        }
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        }
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Product is null");
        }
        if (id > this.repository.findAll().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find this product");
        }
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields are missing");
        }
        return this.repository.update(id, product.getName(), product.getCategory(), product.getPrice());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product deleteProduct(@PathVariable int id) {
        if (id > this.repository.findAll().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find this product");
        }
        return this.repository.delete(id);
    }
}
