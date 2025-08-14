
package com.booleanuk.api.bagels;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("product")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }


    @GetMapping
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Product findID(@PathVariable int id) {
        Product product = this.repository.find(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        Product response = this.repository.create(product);
        if (response != null){
            return response;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is a bad psot request");



    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id,@RequestBody Product product ){

        Product productToUpdate = this.repository.update(id, product);
        if (productToUpdate != null)
            return productToUpdate;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");


    }


    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id){
        Product prodDeleted = repository.find(id);
        if (prodDeleted != null){
            this.repository.delete(id);
            return prodDeleted;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID not found");

    }

    @GetMapping("sorted")
    public List<Product> getAllSorted() {

        return this.repository.findAllSorted();
    }


//    @GetMapping("/{id}")
//    public Bagel find(int id) {

}
