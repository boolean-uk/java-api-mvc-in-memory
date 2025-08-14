package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.repositories.ProductRepository;
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
    public List<Product> getAll(@RequestParam(name = "category", required = false, defaultValue = "") String category){
        if(category.isEmpty())
            return this.theProducts.findAll();
        List<Product> products = this.theProducts.findAll(category);
        if (products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of that category");
        }
        return products;
    }


    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name="id") int id) {
        Product product = this.theProducts.find(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found!");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@RequestBody Product product){
        if (this.theProducts.addProduct(product))
            return product;
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable (name = "id") int id, @RequestBody Product product) {
        Product pr = theProducts.update(id, product);
        if (pr == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pr;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        Product pr = this.theProducts.delete(id);
        if (pr == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pr;
    }
}
