package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Price must be an integer, something else was provided.")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleException(HttpMessageNotReadableException ex) { }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if(this.repository.findAll().stream().anyMatch(x -> x.getName().equalsIgnoreCase(product.getName()))) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        if(category == null) {
            return this.repository.findAll();
        } else {
            List<Product> products = this.repository.findAll().stream().filter(x -> x.getCategory().equalsIgnoreCase(category)).toList();
            if (products.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
            return products;
        }
    }
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product product = this.repository.getOne(id);
        if(product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        if(this.repository.find(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        Product productUpdated = this.repository.update(id, product);
        if(productUpdated == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        return productUpdated;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable(name = "id") int id) {
        Product product = this.repository.delete(id);
        if(product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        return product;
    }
}
