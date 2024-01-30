package com.booleanuk.api.Product.Controller;

import com.booleanuk.api.Product.Models.Product;
import com.booleanuk.api.Product.Repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll(@RequestParam(name = "category", required = false) String category) {
        return this.theProducts.getAll(category);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return this.theProducts.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.theProducts.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id) {
        return this.theProducts.delete(id);
    }



}
