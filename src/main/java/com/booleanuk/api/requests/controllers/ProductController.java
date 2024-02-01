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

    public ProductController() {
        this.repository = new ProductRepository();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product productToBeCreated) {
        validateString(productToBeCreated.getName());
        validateString(productToBeCreated.getCategory());

        productAlreadyExists(productToBeCreated);

        Product createdProduct = this.repository.createProduct(productToBeCreated.getName(), productToBeCreated.getCategory(), productToBeCreated.getPrice());

        if(createdProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product could not be created.");
        }

        return createdProduct;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(required = false) String category) {
        List<Product> allProducts = null;

        if(category == null) {
            allProducts = this.repository.findAll();
        }
        else {
            allProducts = this.repository.findAllOfSpecificCategory(category);
        }

        if (allProducts == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products were found.");
        }

        return allProducts;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getSpecificProduct(@PathVariable int id) {
        validateId(id);

        Product product = this.repository.find(id);
        searchProduct(product);


        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product productToBeUpdated) {
        validateId(id);
        validateString(productToBeUpdated.getName());
        validateString(productToBeUpdated.getCategory());

        Product productToSearchFor = this.repository.find(id);
        searchProduct(productToSearchFor);

        productAlreadyExists(productToBeUpdated);

        Product updatedProduct = this.repository.updateProduct(id, productToBeUpdated.getName(), productToBeUpdated.getCategory(), productToBeUpdated.getPrice());

        if(updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product could not be updated.");
        }

        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id) {
        validateId(id);

        Product product = this.repository.find(id);
        searchProduct(product);

        this.repository.deleteProduct(id);

        return product;
    }


    private void validateId(int id) {
        if(id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not valid.");
        }
    }
    private void validateString(String string) {
        if (string == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, string + " cannot be null.");
        }
    }

    private void searchProduct(Product product) {
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
    }

    private void productAlreadyExists(Product product) {
        for (Product p : this.repository.findAll()) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }
    }
}
