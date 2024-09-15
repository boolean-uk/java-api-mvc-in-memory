package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repo;

    public ProductController() {
        repo = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProds(@RequestParam(required = false) String category){
        if (category != null){
            if (this.repo.getCategory(category) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No products found");
            }
            return this.repo.getCategory(category);
        }
        return this.repo.getAll();
    }

    @GetMapping("/{id}")
    public Product getOneProd(@PathVariable int id) {
        if (this.repo.getOne(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }
        return this.repo.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProd(@RequestBody Product newProduct) {
        if (this.repo.checkName(newProduct.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product already exists");
        }
        return this.repo.addNew(newProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProd(@PathVariable int id, @RequestBody Product newProduct) {
        if (this.repo.checkName(newProduct.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product name already exists");
        }
        if (this.repo.update(id,newProduct) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }
        return this.repo.update(id,newProduct);
    }

    @DeleteMapping("/{id}")
    public Product deleteProd(@PathVariable int id) {
        if (this.repo.getOne(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }
        return this.repo.delete(id);
    }

}
