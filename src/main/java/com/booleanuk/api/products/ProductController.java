package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repo;

    public ProductController() {
        repo = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProds(){
        return this.repo.getAll();
    }

    @GetMapping("/{id}")
    public Product getOneProd(@PathVariable int id) {
        return this.repo.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProd(@RequestBody Product newProduct) {
        return this.repo.addNew(newProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProd(@PathVariable int id, @RequestBody Product newProduct) {
        return this.repo.update(id,newProduct);
    }

    @DeleteMapping("/{id}")
    public Product deleteProd(@PathVariable int id) {
        return this.repo.delete(id);
    }

}
