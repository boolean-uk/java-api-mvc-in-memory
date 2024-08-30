package com.booleanuk.api.controller;

import com.booleanuk.api.exceptions.NoProductsInCategoryException;
import com.booleanuk.api.exceptions.ProductNameExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;
import com.booleanuk.api.model.Product;
import com.booleanuk.api.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 code
    public Product create(@RequestBody Product newProduct) {
        Product toAdd = null;

        try {
            toAdd = this.productRepository.add(newProduct);
        } catch (ProductNameExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return toAdd;
    }

    @GetMapping
    public ArrayList<Product> getAll(@RequestBody(required = false) Product productWithCategory) {
        ArrayList<Product> toReturn = null;
        String givenCategory = "";
        if (productWithCategory != null) {
            givenCategory = productWithCategory.getCategory();
            try {
                toReturn = this.productRepository.getAll(givenCategory);
            } catch (NoProductsInCategoryException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            toReturn = this.productRepository.getAll(null);
        }
        return toReturn;
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable int id) {
        Product toReturn = null;
        try {
            toReturn = this.productRepository.getOne(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return toReturn;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED) // 201 code
    public Product updateProduct(@PathVariable int id, @RequestBody Product newProductData) {
        Product updatedProduct = null;
        try {
            updatedProduct = this.productRepository.update(id, newProductData);
        } catch (ProductNameExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return updatedProduct;
    }

    @DeleteMapping("{id}")
    public Product delete(@PathVariable int id) {
        Product actualProduct = null;
        try {
            actualProduct = this.productRepository.remove(id);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return actualProduct;
    }

}
