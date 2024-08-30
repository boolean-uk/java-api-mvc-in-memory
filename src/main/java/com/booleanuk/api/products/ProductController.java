//ProductController
package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController(){

        this.theProducts = new ProductRepository();

    }

    @GetMapping
    public ArrayList<Product> getAll(){

        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable (name = "id") int id){
        return this.theProducts.getOne(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){

        return this.theProducts.createOne(product);

    }

    @PutMapping("/{id}")
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product) {
        return this.theProducts.updateOne(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {
        this.theProducts.deleteOne(id);
    }

}


