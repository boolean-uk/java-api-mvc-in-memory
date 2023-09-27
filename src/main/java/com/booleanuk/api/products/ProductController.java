package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepo;

    public ProductController() {
        productRepo = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProducts(@RequestParam (required = false) String category){
        ArrayList<Product> selectedProducts;
        if (category == null){
            selectedProducts = this.productRepo.findAll();
        } else {
            selectedProducts = this.productRepo.findByCategory(category);
        }
        if (selectedProducts == null && category == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"No products were found.");
        } else if (selectedProducts == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"No products of provided category were found.");
        }
        return selectedProducts;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody Product product){
        Product toBeCreated = this.productRepo.create(product);
        if (toBeCreated == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400),"Product with provided name already exists.");
        }
        return toBeCreated;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        Product toBeFound = this.productRepo.find(id);
        if (toBeFound == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Product not found.");
        }
        return toBeFound;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product putProduct(@PathVariable int id, @RequestBody Product product){
        try {
            Product toBeUpdated =  this.productRepo.update(id, product);
            if (toBeUpdated == null){
                throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Product not found.");
            }
            return toBeUpdated;
        } catch (InstanceAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        Product toBeDeleted = this.productRepo.delete(id);
        if (toBeDeleted == null){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Product not found.");
        }
        return toBeDeleted;
    }
}
