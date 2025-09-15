package com.booleanuk.api.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.booleanuk.api.Product.ProductRepository;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {
        List<Product> products = this.repository.findAll();
        if (products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found");
        }
        return products;
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        List<Product> items = this.getAll();

        boolean exists = items.stream()
                .anyMatch(item -> item.getName().equals(product.getName()));

        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }

        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @GetMapping("{id}")
    public Product findId(@PathVariable int id) {
        Product product = this.repository.find(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return product;
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){

        Product existing = this.repository.find(id);

        if (existing == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        boolean nameExists = this.repository.findAll().stream()
                .anyMatch(p -> !(p.getId() == id) && p.getName().equals(product.getName()));

        if (nameExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }

        return this.repository.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        Product delete = this.repository.deleteProduct(id);

        if (delete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return delete;
    }

}
