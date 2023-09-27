package com.booleanuk.api.requestsProducts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository theProducts;

    public ProductController() {
    this.theProducts = new ProductRepository();
    }

    @GetMapping
    public ArrayList<Product> getAllProducts(@RequestParam(required = false) String category) {
        ArrayList<Product> products;

        if (category != null) {
            if (this.theProducts.getByCategory(category).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products provided by the category were found.");
            }
        }
        return this.theProducts.getAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That product doesn't exist");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.theProducts.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.theProducts.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
       return this.theProducts.deleteProduct(id);
    }
}
