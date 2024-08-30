package com.booleanuk.api.controllers;

import com.booleanuk.api.exceptions.ErrorResponse;
import com.booleanuk.api.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;
import com.booleanuk.api.models.Product;
import com.booleanuk.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController(){
        this.theProducts = new ProductRepository();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            Product createdProduct = theProducts.create(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (ProductAlreadyExistsException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(value = "category", required = false) String category){
        if(category != null && !category.isEmpty()){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(this.theProducts.getCategory(category));
            } catch (ProductNotFoundException e){
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.theProducts.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.theProducts.getOne(id));
        } catch (ProductNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Product product){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.theProducts.update(id, product));
        } catch (ProductNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (ProductAlreadyExistsException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.theProducts.delete(id));
        } catch (ProductNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
