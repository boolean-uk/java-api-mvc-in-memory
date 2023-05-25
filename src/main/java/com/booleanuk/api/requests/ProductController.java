package com.booleanuk.api.requests;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController(){
        this.theProducts = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product p){
        ArrayList<Product> products = this.theProducts.getAll();
        for (Product existingProduct : products){
            if (existingProduct.getName().equals(p.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }

        if (p.getPrice() == (int) p.getPrice()){
            return this.theProducts.create(p);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be an integer");
        }
    }

    @GetMapping
    public ArrayList<Product> getAll(@RequestParam(required = false) String category){
        if (category != null){
            ArrayList<Product> products = this.theProducts.getAll(category);
            if (products == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No products of the provided category were found");
            }
            return products;
        }
        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable (name = "id") int id){
        Product product = this.theProducts.getOne(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable (name = "id") int id, @RequestBody Product p){
        Product product = this.theProducts.update(id, p);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        if (product.getPrice() != (int) product.getPrice()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ArrayList<Product> products = this.theProducts.getAll();
        for (Product existingProduct : products){
            if (existingProduct.getName().equals(p.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }

        return product;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id){
        Product product = this.theProducts.delete(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        return product;
    }
}
