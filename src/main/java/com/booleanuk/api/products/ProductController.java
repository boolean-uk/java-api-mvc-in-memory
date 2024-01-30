package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {

        return this.theProducts.create(product);
    }
    @GetMapping
    public ArrayList<Product> getAll() {

        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {

        return this.theProducts.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable(name = "id") int id,@RequestBody Product product) {

        return this.theProducts.update(id,product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {

        return this.theProducts.delete(id);
    }
}
