package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository products;

    public ProductController(){
        products = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(){
        return products.getProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id){
        return products.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        products.addProduct(product);

        return products.getProduct(product.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product updated){
        Product product = products.getProduct(id);

        if (product != null){
            product.setName(updated.getName());
            product.setCategory(updated.getCategory());
            product.setPrice(updated.getPrice());

            return products.getProduct(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        if (products.getProduct(id) != null){
            return products.deleteProduct(id);
        }

        return null;
    }
}
