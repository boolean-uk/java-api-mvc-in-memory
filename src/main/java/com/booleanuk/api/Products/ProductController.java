package com.booleanuk.api.Products;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductRepository products;

    public ProductController( ) {
        this.products = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product createProduct) {
        return products.createProduct(createProduct);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSpecificProduct(@PathVariable int id) {
       return products.getSpecificProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
       return products.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id) {
        return products.deleteProduct(id);
    }

}