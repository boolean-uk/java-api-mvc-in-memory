package com.booleanuk.api.Products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(value = "category", required = false) String category) {
        if (category != null) {
            List<Product> products = this.repository.getAllByCategory(category);
            if (products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
            }
            return products;
        }
        return this.repository.getAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable("id") int id) {
        Product product = this.repository.find(id);
        return product;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product putProduct(@PathVariable("id") int id,  @RequestBody Product updatedProduct ) {
        if(this.repository.find(updatedProduct.getName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }else{
            Product product = this.repository.find(id);
            if(product != null){
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }else{
                return null;
            }
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProductFirstName(@PathVariable("id") int id) {
        return this.repository.delete(id);
    }



}
