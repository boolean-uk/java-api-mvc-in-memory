package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepo;

    public ProductController() {
        productRepo = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProducts(){
        return this.productRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product){
        return this.productRepo.create(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return this.productRepo.find(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product putProduct(@PathVariable int id, @RequestBody Product product){
        return this.productRepo.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        return this.productRepo.delete(id);
    }
}
