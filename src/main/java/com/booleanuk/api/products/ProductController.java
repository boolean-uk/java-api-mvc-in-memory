package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {


    private ProductRepository theProduct;

    public ProductController() {
        this.theProduct = new ProductRepository();


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        this.theProduct.create(product.getName(),product.getCategory(), product.getPrice());
        return product;
    }

    @GetMapping
    public List<Product> getAll() {
        return this.theProduct.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return this.theProduct.find(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return this.theProduct.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.theProduct.delete(id);
    }




}
