package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.channels.ScatteringByteChannel;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("products")

public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        if (this.theProducts.getAll(category) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found!");
        }
        return this.theProducts.getAll(category);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        Product toReturn = this.theProducts.createProduct(product);
        if (toReturn == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists!");
        }
        return toReturn;
    }

    //try-catch not needed at all but I just wanted to try it
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        try {
            return this.theProducts.getOneProduct(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        try {
            Product toReturn = this.theProducts.updateProduct(id, product);
            if (toReturn == null) {
                throw new IllegalArgumentException("Item already exists");
            }
            return toReturn;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public Product delete(@PathVariable(name = "id") int id) {
        try {
            return this.theProducts.deleteProduct(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
