package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(){
        productRepository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        if(productRepository.create(product)==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price must be an Integer, or something else was provided/Product with provided name already exists.");
        }
        return productRepository.create(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(){
        return productRepository.getAll();
    }
    @GetMapping("allByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@PathVariable (name = "category") String category){
        List<Product> specific = this.productRepository.getByCategory(category);
        if(specific.size() ==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return specific;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getByID(@PathVariable(name = "id") int id){
        if(this.productRepository.getByID(id)==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return productRepository.getByID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable(name = "id") int id, @RequestBody Product product){
        Product temp = productRepository.update(id, product);
        if(temp == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Price must be an integer, something else was provided. / Product with provided name already exists.");
        }
        if(temp.getPrice()==-1){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
        }
        return temp;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable(name = "id") int id){
        Product removed = productRepository.delete(id);
        if(removed==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        }
        return removed;
    }
}
