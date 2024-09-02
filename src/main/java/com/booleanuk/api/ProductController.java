package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(){
        this.productRepository = new ProductRepository();
    }

    @GetMapping List<Product> getAll(@RequestParam(required = false) String category){
        return this.productRepository.findAll(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        this.productRepository.create(product.getName(), product.getCategory(), product.getPrice());
        return product;
    }

    @GetMapping("/{id}")
    public Product getSpecificProduct(@PathVariable int id){
        return this.productRepository.findSpecificProduct(id);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        return this.productRepository.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        Product newProduct = this.productRepository.update(id, product);

        if(newProduct == null){
            for (int i = 0; i < productRepository.getProducts().size(); i++){

            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product with the ID: " + id + " could not be found in repository");
        }
        return newProduct;
    }
}
