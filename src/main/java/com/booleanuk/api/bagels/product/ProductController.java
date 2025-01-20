package com.booleanuk.api.bagels.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    @ResponseBody
    public ArrayList<Product> getAllProducts(@RequestParam(required = false) String category) {
        ArrayList<Product> newList = this.theProducts.getAll(category);
        if(newList.isEmpty() && category != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return newList;
    }

    @GetMapping("{id}")
    public Product getOneProduct(@PathVariable int id) {
        Product theProduct = this.theProducts.getOne(id);
        if(theProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return theProduct;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product newProduct = this.theProducts.create(product);
        if(newProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        return newProduct;
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {

        if(!this.theProducts.checkIfNameIsUnique(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
        }

        Product theProduct = this.theProducts.update(id, product);
        if(theProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return theProduct;
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product theProduct = this.theProducts.delete(id);
        if(theProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return theProduct;
    }
}
