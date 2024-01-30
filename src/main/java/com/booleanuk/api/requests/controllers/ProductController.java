package com.booleanuk.api.requests.controllers;

import com.booleanuk.api.requests.models.Product;
import com.booleanuk.api.requests.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;

    public ProductController() {
        this.theProducts = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        String creationMessage = this.theProducts.addProduct(product);

        if (creationMessage.equals("exists")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists!");
        } else if (creationMessage.equals("error")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product could not be created!");
        }

        return product;
    }

    @GetMapping
    public List<Product> showAllProductsFromCategory(@RequestParam(required = false) String category) {
        if (category == null) {
            return this.theProducts.getAllProducts();
        }
        List<Product> productsFromCategory = this.theProducts.getProductsWithCategory(category);
        if (productsFromCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found!");
        }
        return productsFromCategory;
    }

    @GetMapping("{id}")
    public Product getOneProduct(@PathVariable int id) {
        Product product = this.theProducts.getSpecificProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        return product;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProductInfo(@PathVariable int id, @RequestBody Product product) {
        String updateMessage = this.theProducts.changeProductInfo(id, product);
        if (updateMessage.equals("exists")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists!");
        } else if (updateMessage.equals("error")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        return product;
    }

    @DeleteMapping("{id}")
    public Product deleteOneProduct(@PathVariable int id) {
        Product deletedProduct = this.theProducts.removeProduct(id);
        if (deletedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        return deletedProduct;
    }
}
