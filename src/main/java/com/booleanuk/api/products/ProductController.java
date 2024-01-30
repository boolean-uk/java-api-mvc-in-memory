package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController(){
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam (required = false) String category) {
        List<Product> products = this.theProducts.getAll(category);
        if (products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return products;
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        for (Product currProduct : this.theProducts.getAll(product.getCategory())){
            if (currProduct.getName().equals(product.getName())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product already exists");
            }
        }
        return this.theProducts.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product editProduct(@PathVariable(name = "id") int id, @RequestBody Product product){
        Product canUpdate = theProducts.getOne(id);
        boolean productExists = theProducts.getAll(product.getCategory()).contains(canUpdate);
        if (!productExists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product doesn't exists");
        }

        for (Product currProduct : theProducts.getAll(product.getCategory())){
            if (currProduct.getName().equals(product.getName())){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with same name exists");
            }
        }
        return this.theProducts.edit(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable(name="id") int id){
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return this.theProducts.delete(id);
    }

}
