package com.booleanuk.api.coreExtension.Controller;

import com.booleanuk.api.coreExtension.Item.Product;
import com.booleanuk.api.coreExtension.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ArrayList<Product>> getAll() {
        return ResponseEntity.ok(this.theProducts.getAll());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        return ResponseEntity.ok(this.theProducts.getOne(id));
    }
    @PostMapping
    public Product create(@RequestBody Product product){
        return this.theProducts.create(product);
    }
    @PutMapping("/{id}")
    public Product updateproduct(@PathVariable int id, @RequestBody Product product){
        return this.theProducts.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public List<Product> deleteproduct(@PathVariable int id){
        return this.theProducts.deleteProduct(id);
    }
}
