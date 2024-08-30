package com.booleanuk.api.bagels.controllers;

import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repositories.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductsController {
    private ProductsRepository theProducts;

    public ProductsController(){
        this.theProducts = new ProductsRepository();
    }

    @GetMapping
    public ArrayList<Product> findAll(@RequestParam (required = false) String category){
        ArrayList<Product> categoryProductList = this.theProducts.findAll(category);

        if (categoryProductList != null){
            return categoryProductList;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product of the provided category were found");
    }

    @GetMapping("{id}")
    public Product find(@PathVariable int id){
        Product product = this.theProducts.find(id);

        if (product != null){
            return product;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        Product newProduct = this.theProducts.create(product);
        if (newProduct != null){
            return newProduct;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");

    }

    @PutMapping("{id}")
    public Product update(@PathVariable int id, @RequestBody Product product){
        Product updatedProduct;

        try {
            updatedProduct = this.theProducts.update(id, product);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists.");
        }

        if (updatedProduct != null){
            return updatedProduct;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with that ID was found");
    }

    @DeleteMapping("{id}")
    public Product remove(@PathVariable int id){
        Product removedProduct = this.theProducts.remove(id);

        if (removedProduct != null){
            return removedProduct;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

}
