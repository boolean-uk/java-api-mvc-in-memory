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
    public ResponseEntity<ArrayList<Product>> getAll(@RequestParam(value="category") String category) {
        if (this.theProducts.getCategory(category) == null){
            if (this.theProducts.getAll() == null){
                throw new ResponseStatusException(HttpStatus.valueOf(404), "Not found.");
            }
            return ResponseEntity.ok(this.theProducts.getAll());
        }
        return ResponseEntity.ok(this.theProducts.getCategory(category));
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
    public Product create(@RequestBody Product product){

        return this.theProducts.create(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        if(this.theProducts.isPresent(id)) {
            if (theProducts.getNamesAndId(product.getName(), id)){
                throw new ResponseStatusException(HttpStatus.valueOf(400), "Product already exists");
            }
            return ResponseEntity.ok(this.theProducts.updateProduct(id, product));
        }
        throw new ResponseStatusException(HttpStatus.valueOf(404), "Not found.");
    }
    @DeleteMapping("/{id}")
    public List<Product> deleteproduct(@PathVariable int id){
        return this.theProducts.deleteProduct(id);
    }
}
