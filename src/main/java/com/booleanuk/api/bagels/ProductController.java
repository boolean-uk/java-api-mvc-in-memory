package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (product.getCategory() == null || product.getName() == null || product.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        if (this.theProducts.create(product) != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
    }
}
