package com.booleanuk.api.extension;

import com.booleanuk.api.extension.exceptions.ErrorResponse;
import com.booleanuk.api.extension.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.extension.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    ProductController(){
        productRepository = new ProductRepository();
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productRepository.add(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (ProductAlreadyExistsException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(name = "category", required = false) String category) {
        try {
            return ResponseEntity.ok(productRepository.getCategoryAll(category));
        } catch (ProductNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable int id) {
        try {
            Product createdProduct = productRepository.getOne(id);
            return ResponseEntity.ok(createdProduct);
        } catch (ProductNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        try {
            Product updatedProduct = productRepository.update(product, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
        } catch (ProductNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (ProductAlreadyExistsException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) {
        try{
            Product createdProduct = productRepository.delete(id);
            return ResponseEntity.ok(createdProduct);
        } catch (ProductNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
