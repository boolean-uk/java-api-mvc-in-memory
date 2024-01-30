package com.booleanuk.api.coreExtension.Controller;

import com.booleanuk.api.coreExtension.Item.Product;
import com.booleanuk.api.coreExtension.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ArrayList<Product>> getAll(@RequestParam(value = "category", required = false) String category) {
        if (category == null || category.isEmpty()) {
            return ResponseEntity.ok(this.theProducts.getAll());
        } else {
            ArrayList<Product> categoryProducts = this.theProducts.getCategory(category);
            if (categoryProducts.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found in the given category.");
            }
            return ResponseEntity.ok(categoryProducts);
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        if (this.theProducts.getOne(id) == null){
            throw new ResponseStatusException(HttpStatus.valueOf(404), "Not found.");
        }
        return ResponseEntity.ok(this.theProducts.getOne(id));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            Product newProduct = this.theProducts.create(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (!this.theProducts.isPresent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        if (this.theProducts.getNamesAndId(product.getName(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
        }
        Product updatedProduct = this.theProducts.updateProduct(id, product);
        if (updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        List<Product> updatedList = this.theProducts.deleteProduct(id);
        if (updatedList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return ResponseEntity.ok(updatedList);
    }
}
