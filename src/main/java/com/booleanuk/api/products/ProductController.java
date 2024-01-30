package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository repository;


    public ProductController() {
        this.repository = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAll(@RequestParam(name = "category", required = false) String category) {
        ArrayList<Product> temp;
        if(category == null){
            return this.repository.findAll("");
        }
        else{
            temp = this.repository.findAll(category.toLowerCase());
            if(temp.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No products of the provided category were found.");
            }
            else{
                return temp;
            }
        }
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = this.repository.findOne(id);
        if (product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with provided name already exists.");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createOne(@RequestBody Product product) {
        if (product.getName() == null || product.getCategory() == null || product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Product temp = repository.createOne(product);
        if (temp != null) {
            return temp;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with provided name already exists.");
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateOne(@PathVariable int id, @RequestBody Product product) {

        if (product.getName() == null && product.getCategory() == null && product.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (this.repository.findOne(product.getName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with provided name already exists.");
        }
        Product p = this.repository.updateOne(id, product);
        if (p != null) {
            return p;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product not found.");
        }
    }

    @DeleteMapping("/{id}")
    public Product deleteOne(@PathVariable int id) {
        Product product = this.repository.deleteOne(id);
        if (product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product not found.");
        }
    }
}
