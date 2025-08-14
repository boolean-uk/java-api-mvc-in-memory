package com.booleanuk.api.Products.controllers;

import com.booleanuk.api.Products.models.Product;
import com.booleanuk.api.Products.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductRepository productRepository;


    public ProductController(){

        productRepository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAllProducts(){

        return productRepository.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getSingleProduct(@PathVariable int id){
        Product p = productRepository.getProduct(id);

        if(p == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

        return p;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNew(@RequestBody Product product){

        this.productRepository.addProduct(product);
        return product;
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product) {

        if (this.productRepository.getProduct(id)==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        Product p = this.productRepository.getProduct(id);
        p.setCategory(product.getCategory());
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        return p;
    }


    @DeleteMapping("{id}")
    public Product delete(@PathVariable int id){

        Product p = this.productRepository.getProduct(id);
        if(p == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        this.productRepository.delete(id);
        return p;
    }
}

