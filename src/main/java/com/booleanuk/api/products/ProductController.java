package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController() {
        this.productRepository = new ProductRepository();
    }
    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category ) {

        if (category !=null ){
            List<Product> foundByCategory = this.productRepository.findByCategory(category);
            if(foundByCategory.size() < 1)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with provided category found.");
            }
            return  foundByCategory;
        }

        return this.productRepository.findAll();
    }
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id) {
        var product =productRepository.find(id);
        if(product==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        Product p =productRepository.findByName(product.getName());
        if(p!=null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists.");
        }

        return this.productRepository.create(product.getName(),product.getCategory(),product.getPrice());
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable (name = "id") int id, @RequestBody Product product) {
        var p =productRepository.find(id);
        if(p==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
//       This check is not necessary because you will always find a product with a name
//        if(productRepository.findByName(p.getName())!=null){
//
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the provided name already exists.");
//        }

        product.setId(id);
        return this.productRepository.update(product);
    }
    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        var product =productRepository.find(id);
        if(product==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        return this.productRepository.delete(id);
    }
}
