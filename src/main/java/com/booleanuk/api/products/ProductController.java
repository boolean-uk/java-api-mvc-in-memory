package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.theProducts.create(product);
    }
    @GetMapping
    public ArrayList<Product> getAll(@RequestParam(required = false) String category) {
        if(this.theProducts.getAll(category).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category doesnt exist!");
        }
        return this.theProducts.getAll(category);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That product is unfortunately not in the list");
        }
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable(name = "id") int id,@RequestBody Product product) {
        Product author1 = this.theProducts.update(id,product);
        if (author1 == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can't update the product, because it does not exist");
        }
        return author1;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        Product product = this.theProducts.delete(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in the list");
        }
        return product;
    }
}
