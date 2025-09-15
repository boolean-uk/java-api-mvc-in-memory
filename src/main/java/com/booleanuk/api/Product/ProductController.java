package com.booleanuk.api.Product;

import org.springframework.web.bind.annotation.*;
import com.booleanuk.api.Product.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    ProductRepository repository;

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll() {return this.repository.findAll();}


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return this.repository.create(product.getName(), product.getCategory(), product.getPrice());
    }

    @GetMapping("{id}")
    public Product findId(@PathVariable int id) {
        return this.repository.find(id);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        return this.repository.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    public Product deleteProduct(@PathVariable int id){
        return this.repository.deleteProduct(id);
    }

}
