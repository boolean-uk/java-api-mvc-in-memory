package com.booleanuk.api.requests.controllers;

import com.booleanuk.api.requests.models.Product;
import com.booleanuk.api.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product productToBeCreated) {
        if(productToBeCreated.getName() == null || productToBeCreated.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields cannot be null.");
        }

        for(Product product : this.repository.findAll()) {
            if(product.getName().equalsIgnoreCase(productToBeCreated.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
            }
        }

        this.repository.createProduct(productToBeCreated.getName(), productToBeCreated.getCategory(), productToBeCreated.getPrice());

        Product createdProduct = null;

        for(Product product : this.repository.findAll()) {
            if(product.getName().equalsIgnoreCase(productToBeCreated.getName())) {
                createdProduct = product;
            }
        }

        if(createdProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product could not be created.");
        }

        return createdProduct;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllInSpecificCategory(@PathVariable String category) {
        if(category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category cannot be null.");
        }

        List<Product> productsOfSpecificCategory = this.repository.findAllOfSpecificCategory(category);

        if(productsOfSpecificCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }

        return productsOfSpecificCategory;
    }

    @GetMapping("/{id]")
    @ResponseStatus(HttpStatus.OK)
    public Product getSpecificProduct(@PathVariable int id) {
        if(id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid.");
        }

        Product product = this.repository.getSpecificProduct(id);

        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product productToBeUpdated) {
        if(id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid.");
        }

        if(productToBeUpdated.getName().isEmpty() || productToBeUpdated.getCategory().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields cannot be null");
        }

        Product productToSearchFor = this.repository.find(id);

        if(productToSearchFor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        for(Product product : this.repository.findAll()) {
            if(product.getName().equalsIgnoreCase(productToBeUpdated.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }

        Product updatedProduct = this.repository.updateProduct(id, productToBeUpdated.getName(), productToBeUpdated.getCategory(), productToBeUpdated.getPrice());

        if(updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product could not be updated.");
        }

        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id) {
        if(id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid.");
        }

        Product product = this.repository.find(id);

        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        Product deletedProduct = this.repository.deleteProduct(id);

        if(deletedProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product could not be deleted.");
        }

        return deletedProduct;
    }
}
