package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController(){
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {
        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product author){
        return this.theProducts.create(author);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product editProduct(@PathVariable(name = "id") int id, @RequestBody Product author){
        return this.theProducts.edit(id, author);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable(name="id") int id){
        return this.theProducts.delete(id);
    }

}
