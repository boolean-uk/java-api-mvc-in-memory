package com.booleanuk.api.bagels;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductsRepository theProducts;

    public ProductsController() {
        this.theProducts = new ProductsRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProducts() {
        return this.theProducts.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return this.theProducts.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.update(id, product.getName(), product.getCategory(), product.getPrice());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product) {
        return this.theProducts.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.theProducts.delete(id);
    }

}
