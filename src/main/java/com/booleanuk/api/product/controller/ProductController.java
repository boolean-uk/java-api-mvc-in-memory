package com.booleanuk.api.product.controller;

import com.booleanuk.api.product.controller.Dto.ProductCreateDto;
import com.booleanuk.api.product.model.pojo.Product;
import com.booleanuk.api.product.model.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController() {
        productRepository = new ProductRepository();

    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String category) {
        return productRepository.getProducts(category);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.getOne(id);
    }


    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto productCreateDto) {
        Product product = new Product(productCreateDto);

        productRepository.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody ProductCreateDto body){
        return productRepository.updateProduct(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable int id) {
        return productRepository.deleteProduct(id);
    }


}
