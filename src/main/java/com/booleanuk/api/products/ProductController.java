package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepo productRepo;

    public ProductController() {
        this.productRepo = new ProductRepo();
    }

    @GetMapping
    public ResponseEntity<ArrayList<HashMap<String,Object>>> getAllProducts() {
        return new ResponseEntity<>(this.productRepo.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getOneProduct(@PathVariable int id) {
        return new ResponseEntity<>(productRepo.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> createAProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepo.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> updateAProduct (@PathVariable int id, @RequestBody Product updatedProduct) {
        return new ResponseEntity<>(productRepo.update(id, updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteAProduct(@PathVariable int id) {
        return new ResponseEntity<>(productRepo.delete(id), HttpStatus.OK);
    }
}
