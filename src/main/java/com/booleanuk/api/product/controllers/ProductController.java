package com.booleanuk.api.product.controllers;

import com.booleanuk.api.product.models.Product;
import com.booleanuk.api.product.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository(new ArrayList<>());
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(defaultValue="") String category){
        if(!category.isEmpty()){
            List<Product> filteredProd = this.productRepository.getAllByCategory(category);
            if(filteredProd.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with that category found");
            }
            return filteredProd;
        }

        List<Product> allProds = this.productRepository.getAll();
        if(allProds.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
        }
        return allProds;
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id){
        Product prod = this.productRepository.getById(id);
        nullCheck(prod, HttpStatus.NOT_FOUND, "Product not found.");
        return prod;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        if(productRepository.checkNameConflict(product)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product with provided name already exists");
        }
        return this.productRepository.add(product);
    }

    @PutMapping("{id}")
    public Product putProduct(@RequestBody Product product, @PathVariable int id){

        if(productRepository.checkNameConflict(product)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product with provided name already exists");
        }

        Product foundProd = this.productRepository.getById(id);
        nullCheck(foundProd, HttpStatus.NOT_FOUND, "Product not found.");
        foundProd.setName(product.getName());
        foundProd.setCategory(product.getCategory());
        foundProd.setPrice(product.getPrice());

        return foundProd;
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable int id){
        Product removed = this.productRepository.removeProduct(id);
        nullCheck(removed, HttpStatus.NOT_FOUND, "Product not found.");
        return removed;
    }

    private void nullCheck(Product product, HttpStatus status, String message){
        if(product == null){
            throw new ResponseStatusException(status, message);
        }
    }

}
