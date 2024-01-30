package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        if(this.theProducts.getAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List is empty");
        }

        if(category == null) {
            return this.theProducts.getAll();
        }
        return this.theProducts.getAllWithCategory(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOne(@PathVariable(name = "id") int id) {
        return this.theProducts.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.theProducts.create(product);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable int id) {
        return theProducts.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return theProducts.update(id, product);
    }
}
