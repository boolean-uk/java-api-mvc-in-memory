package com.booleanuk.api.Products;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        boolean productExists = products.getAllProducts().stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(createProduct.getName()));

        if (productExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Product with provided name already exists.");
        }
        return products.createProduct(createProduct);
    }

    @GetMapping
    public List<Product> getAllProducts() {

        return products.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getSpecificProduct(@PathVariable int id) {
        Product product = this.products.getSpecificProduct(id);
        if (product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product p = this.products.updateProduct(id, updatedProduct);
        if (p != null) {
            return p;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product not found.");
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable int id) {
        Product product = this.products.deleteProduct(id);
        if (product != null) {
            return product;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Product not found.");
        }
    }

}

