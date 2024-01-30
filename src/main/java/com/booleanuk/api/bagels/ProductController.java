package com.booleanuk.api.bagels;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> productList;
        if (category == null) {
            productList = this.theProducts.getAll();
        }else {
            productList = this.theProducts.getAll(category);
        }
        if (!productList.isEmpty()) {
            return productList;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getSpecific(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.getSpecific(id);
        if (product != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.delete(id);
        if (product != null) {
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
    }
}
