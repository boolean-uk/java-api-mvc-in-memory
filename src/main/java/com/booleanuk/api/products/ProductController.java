package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository = new ProductRepository();

    public ProductController()
    {
        this.productRepository = new ProductRepository();
    }
    @GetMapping
    public List<Product> getAll()
    {
        return this.productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id)
    {
        Product findProduct =  this.productRepository.find(id);
        if(findProduct == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return findProduct;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product newProduct)
    {
        return productRepository.create(newProduct.getName(), newProduct.getPrice(), newProduct.getCategory());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable(name = "id") int id, @RequestBody Product product)
    {
        Product newProduct = productRepository.update(product.getName(), product.getPrice(), product.getCategory(), id);
        if(newProduct == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return newProduct;

    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable (name = "id") int id)
    {
        Product product = this.productRepository.delete(id);
        if(product == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }
        return product;
    }
}