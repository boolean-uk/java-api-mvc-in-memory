package com.booleanuk.api.controller;

import com.booleanuk.api.model.ProductRepository;
import com.booleanuk.api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("products")
public class ProductController {
    final ProductRepository repository = new ProductRepository();

    @GetMapping("/{uuid}")
    public Product getProduct(@PathVariable int uuid) {
        try {
            return repository.find(uuid);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<Product> getProductsByCategory(@RequestParam(value = "category", required = false) final String category) {
        final List<Product> _outProducts = category == null || category.isEmpty() ? repository.findAll() : repository.getProductsByCategory(category);
        if (_outProducts.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products in the '" + category + "' category.");
        return _outProducts;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        if (product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided data was null. Could not convert json object.");
        if (!product.isValidProduct()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data mismatch. Some data that was provided was invalid.");
        if (repository.hasProductByName(product.name)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists.");
        repository.create(product);
        return product;
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable final int uuid, @RequestBody Product product) {
        if (product == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided data was null. Could not convert json object.");
        if (!product.isValidProduct()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data mismatch. Some data that was provided was invalid.");

        try {
            Product _matchingProduct = repository.find(uuid);
            _matchingProduct.merge(product);
            return _matchingProduct;
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified product does not exist.");
    }

    @DeleteMapping("/{uuid}")
    public Product removeProduct(@PathVariable final int uuid) {
        try {
            return repository.erase(uuid);
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified product does not exist.");
    }
}
