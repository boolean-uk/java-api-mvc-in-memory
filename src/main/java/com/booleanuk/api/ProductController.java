package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository products;

    public ProductController(){
        products = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category){
        if (category == null){
            return products.getProducts();
        }
        return products.getProducts().stream()
                .filter(product -> product.getCategory().equals(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id){
        Product product = products.getProduct(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        boolean isAdded = products.addProduct(product);

        if (!isAdded){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists.");
        }

        return products.getProduct(product.getId());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable int id, @RequestBody Product updated){
        Product product = products.getProduct(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (products.getProductByName(updated.getName()) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Another product exists with this name.");
        }

        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setPrice(updated.getPrice());

        return products.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable int id){
        if (products.getProduct(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return products.deleteProduct(id);
    }
}
