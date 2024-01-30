package com.booleanuk.api.products;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product product) {
        this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @GetMapping
    public List<Product> getAll() { return this.repository.findAll();}

    @GetMapping("/{id}")
    public Product getSpecific(@PathVariable int id) {
        return this.repository.find(id);
    }

    @DeleteMapping("/{id}")
    public Product removeProduct(@PathVariable int id) {
        return this.repository.remove(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        return this.repository.update(id, updatedProduct);
    }


}
