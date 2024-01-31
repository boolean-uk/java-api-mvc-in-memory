package com.booleanuk.api.products;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;


    public ProductController() {
        this.repository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {

        if(category != null) {
            if(this.repository.categoryInData(category)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return this.repository.findAll(category);
        }
        return this.repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody Product createdProduct) {
        if (this.repository.nameInData(createdProduct.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        this.repository.create(createdProduct.getName(), createdProduct.getCategory(), createdProduct.getPrice());
    }


    @GetMapping("/{id}")
    public Product getSpecific(@PathVariable int id) {
        if(this.repository.idInData(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.repository.find(id);
    }

    @DeleteMapping("/{id}")
    public Product removeProduct(@PathVariable int id) {
        if(this.repository.idInData(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.repository.remove(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        if(!this.repository.idInData(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(this.repository.nameInData(updatedProduct.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.repository.update(id, updatedProduct);
    }


}
