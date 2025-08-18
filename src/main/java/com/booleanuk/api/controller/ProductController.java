package com.booleanuk.api.controller;

import com.booleanuk.api.model.Product;
import com.booleanuk.api.model.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepo authorDB;

    public ProductController(){
        this.authorDB = new ProductRepo();
    }

    @GetMapping("")
    public List<Product> getAll() {
        return this.authorDB.getAll();
    }

    @GetMapping("{/category}")
    public List<Product> getByCategory(@RequestParam String category) {
        return this.authorDB.getByCategory(category);
    }

    @GetMapping("/{id}")
    public Product getForID(@PathVariable (name="id") int id){
        return this.authorDB.getForID(id);
    }

    @PutMapping("/{id}")
    public Product updateForID(@PathVariable (name="id") int id, @RequestBody Product product){
        return this.authorDB.update(id, product);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteForID(@PathVariable (name="id") int id){
        this.authorDB.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping()
    public Product create(@RequestBody Product product){
        return this.authorDB.create(product);
    }
}
