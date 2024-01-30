package com.booleanuk.api.bagels.controller;

import com.booleanuk.api.bagels.models.Product;
import com.booleanuk.api.bagels.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository theProduct;

    public ProductController(){
        this.theProduct = new ProductRepository();
    }
    @GetMapping
    public List<Product> getAll(){
        return this.theProduct.getAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        if (product.getType() == null || product.getPrice() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"One of the variable names are written wrong");
        }

        if (this.theProduct.getAll().contains(product)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product already exists");
        }

        return this.theProduct.created(product);
    }

    @GetMapping("/{id}")
    public Product getOneAuthor(@PathVariable int id){
        Product product= this.theProduct.getOneAuthor(id);
        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product product){
        return this.theProduct.update(id,product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){

        return this.theProduct.delete(id);
    }

}
