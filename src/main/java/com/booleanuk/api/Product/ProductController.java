package com.booleanuk.api.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "category", required = false)String category ) {
        List<Product> products;
        if(category != null){
            products = this.theProducts.getAll(category);
        }
        else{
            products = this.theProducts.getAll();
        }


        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return products;
    }


    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable(name = "id") int id) {

        if (this.theProducts.getOne(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return this.theProducts.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable(name = "id") int id, @RequestBody Product newProduct){

        if(this.theProducts.chechNameAvailable(newProduct.getName(), id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name already exists");
        }
        if (this.theProducts.updateProduct(id, newProduct) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return newProduct;
    }

    @DeleteMapping("/{id}")
    public Product removeProduct(@PathVariable(name = "id") int id){

        Product product = this.theProducts.removeProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){

        if(this.theProducts.chechNameAvailable(product.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name already exists");
        }
        if (this.theProducts.createProduct(product) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }
}