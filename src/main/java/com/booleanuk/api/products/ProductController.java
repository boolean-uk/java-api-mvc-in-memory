package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository products;

    public ProductController() {
        this.products = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if (product.getPrice() != (int) product.getPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be an integer, something else was provided. / Product with provided name already exists.");
        }
        Product prodResult = this.products.create(product);
        if (prodResult == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be an integer, something else was provided. / Product with provided name already exists.");
        }
        return prodResult;
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> productResults;
        if (category == null) {
            productResults = this.products.getAll();
        } else {
            productResults = this.products.getAllByCategory(category);
            if (productResults.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }

        return productResults;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product productResult = products.findById(id);

        if (productResult == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }

        return products.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable int id) {
        if (product.getPrice() != (int) product.getPrice()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be an integer, something else was provided. / Product with provided name already exists.");
        }
        if (this.products.getAll().stream().noneMatch(product1 -> {return product1.getId()==id;})) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        for (Product prod : products.getAll()) {
            if (prod.getName().equals(product.getName()) && (prod.getId() != id)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be an integer, something else was provided. / Product with provided name already exists.");

            }
        }

        return this.products.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        if (this.products.getAll().stream().noneMatch(product1 -> {return product1.getId()==id;})) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return products.delete(id);
    }
}
