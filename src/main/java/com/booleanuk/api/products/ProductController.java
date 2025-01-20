package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name="category", required = false) String category) {
        if (category == null) {
            return this.theProducts.getAll();
        }

        List<Product> pCategory = this.theProducts.getProductsInCategory(category);
        if (pCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return pCategory;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if (this.theProducts.isNameInList(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
        }
        return this.theProducts.create(product);
    }

    @GetMapping("/{id}")
    public Product getAProduct(@PathVariable int id) {
        Product product = this.theProducts.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateAproduct(@PathVariable int id, @RequestBody Product product) {
        if (theProducts.isNameInList(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
        }
        Product pToUpdate = this.theProducts.update(id, product);
        if (pToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product product = this.theProducts.delete(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }
}
