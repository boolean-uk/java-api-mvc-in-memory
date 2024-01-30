/*
package com.booleanuk.api.bagels;

import com.booleanuk.api.repositories.ProductRepository;
import com.booleanuk.api.requests.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class BagelController {
    ProductRepository repository;

    public BagelController(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product productRequest) {
        Product product = repository.create(productRequest.getType(), productRequest.getPrice());
        return product;
    }

    @GetMapping("/{id}")
    public Product getSpecificBagel(@PathVariable int id) {
        return this.repository.find(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteBagel(@PathVariable int id) {
        return this.repository.delete(id);
    }
}
*/
