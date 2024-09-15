package com.booleanuk.api.requests.controllers;

import com.booleanuk.api.requests.models.Product;
import com.booleanuk.api.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository rep;

    public ProductController() {
        this.rep = new ProductRepository();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Product> getAllProducts(String category) {
        ArrayList<Product> products = this.rep.getAllProducts();

        // If category is not provided, or is empty, return all products
        if (category == null || category.isEmpty()) {
            return products;
        }

        // Filter products by the provided category
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }

        // If no products found for the given category, throw a 404 Not Found
        if (filteredProducts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }

        return filteredProducts;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getOneProduct(@PathVariable(name = "id") int id) {
        Product product = this.rep.getOneProduct(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        for (Product products : this.rep.getAllProducts()) {
            if (products.getName().equals(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
            }
        }
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
        }
        return this.rep.createProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProductById(@PathVariable int id, @RequestBody Product product) {
        ArrayList<Product> productsList = this.rep.getAllProducts();

        for(Product products : productsList) {
            if (products.getName().equals(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
            }
            else if(products == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
            }
        }

        return this.rep.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProductById(@PathVariable int id) {
        ArrayList<Product> products = this.rep.getAllProducts();

        if (products.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }

        return this.rep.deleteProduct(id);
    }
}
