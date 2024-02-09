package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if (productRepository.exists(product.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
        }
        return productRepository.create(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String category) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productRepository.getAll(category);
        } else {
            products = productRepository.getAll(null);
        }
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productRepository.getOne(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }
        return productRepository.edit(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable int id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }
        productRepository.delete(id);
    }
}
