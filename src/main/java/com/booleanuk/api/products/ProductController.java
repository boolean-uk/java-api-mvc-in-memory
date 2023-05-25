package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository repo;

    public ProductController() {
        this.repo = new ProductRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {
        List<Product> products = repo.getProducts();
        if (products.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");

        return products;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(name="id") int id) {
        return repo.getProduct(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id=%d not found", id)));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product putProduct(@PathVariable(name="id") int id, @RequestBody Product product) {
        repo.putProduct(id, product);

        return repo.getProduct(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id=%d not found", id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product) {
        return repo.postProduct(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable(name="id") int id) {
        Product deletedProduct = repo.deleteProduct(id);
        if (deletedProduct == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id=%d not found", id));
        return deletedProduct;
    }
}
