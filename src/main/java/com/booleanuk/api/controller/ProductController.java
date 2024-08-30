package com.booleanuk.api.controller;

import com.booleanuk.api.model.Product;
import com.booleanuk.api.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepo;

    public ProductController(){
        productRepo = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        productRepo.addProduct(product);
        return product;
    }

    @GetMapping
    public ArrayList<Product> getProducts(@RequestParam(required = false) String cat){
        if (cat == null){
            return productRepo.getAll();
        }
        return productRepo.getAll(cat);
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable int id){
        return productRepo.getOne(id);
    }

    @PutMapping("{id}")
    public Product edit(@PathVariable int id, @RequestBody Product newProduct){
        Product updated = this.productRepo.edit(id, newProduct);
        return updated;
    }

    @DeleteMapping("{id}")
    public Product remove(@PathVariable int id){
        return this.productRepo.remove(id);
    }




}
