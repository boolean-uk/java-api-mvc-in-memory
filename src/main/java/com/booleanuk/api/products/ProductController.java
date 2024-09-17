package com.booleanuk.api.products;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository repository;

    public ProductController() {
        repository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Product findOneProduct(@PathVariable int id) {
        return repository.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return repository.update(id, product.getName(), product.getCategory(), product.getPrice());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Product deleteProduct(@PathVariable int id) {
        return repository.delete(id);
    }
}
