package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.models.ProductNotFoundException;
import com.booleanuk.api.repositories.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;


@RestController
@RequestMapping("products")
public class ProductsController {
    private ProductsRepository theProducts;


    public ProductsController() {
        this.theProducts = new ProductsRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProducts(@RequestParam(required = false) String category) {
        ArrayList<Product> products = (category == null) ? this.theProducts.getAll() : filterByCategory(category);
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found for the given category");
        }
        return products;
    }

    private ArrayList<Product> filterByCategory(String category) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : this.theProducts.getAll()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product newProduct) {
        try {
            return this.theProducts.createProduct(newProduct);
        } catch (ProductNotFoundException.ProductAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{id}")
    public Product getSpecificProduct(@PathVariable int id) {
        try {
            return this.theProducts.getOne(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updateProduct) {
        try {
            return this.theProducts.updateProduct(id, updateProduct);
        } catch (ProductNotFoundException | ProductNotFoundException.ProductAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable int id) {
        boolean deleted = this.theProducts.deleteProduct(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found");
        }
    }


}
