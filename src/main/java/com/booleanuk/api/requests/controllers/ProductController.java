package com.booleanuk.api.requests.controllers;

import com.booleanuk.api.requests.models.Product;
import com.booleanuk.api.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        for(Product p : repository.getAll()){
            if(p.getName().equalsIgnoreCase(product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product named " + product.getName() + " already exists");
            }
        }
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }


    @GetMapping
    List<Product> getAll(@RequestParam (name = "category", required = false) String category){
        if (category == null){
            return this.repository.getAll();
        }
        List<Product> returnList = this.repository.getAll(category);
        if(!returnList.isEmpty()){
            return returnList;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no products in the: " + category + " category");
    }


    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product productToGet = this.repository.getOne(id);
        if (productToGet != null){
            return productToGet;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " was not found");
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product){
        for(Product p : repository.getAll()){
            if(p.getName().equalsIgnoreCase(product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product named " + product.getName() + " already exists");
            }
        }
        if (this.repository.update(id, product) != null){
            return this.repository.getOne(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " was not found");
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        Product productToDelete = this.repository.delete(id);
        if (productToDelete != null){
            return productToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " was not found");
    }

}
