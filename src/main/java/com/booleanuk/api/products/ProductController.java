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
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> products = theProducts.getAll(category);
        if(products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return products;
    }
    
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.getOne(id);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        if(theProducts.getProductByName(product.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        return this.theProducts.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        Product currentProductWithNewName = theProducts.getProductByName(product.getName());
        if(currentProductWithNewName != null && currentProductWithNewName.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        Product updatedProduct = this.theProducts.update(id, product);
        if(updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        Product product = this.theProducts.delete(id);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product;
    }
}