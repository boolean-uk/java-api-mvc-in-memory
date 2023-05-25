package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepo;

    public ProductController() {
        this.productRepo = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductDTO productDTO)  {
        try {
            Integer.parseInt(productDTO.getPrice());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be integer");
        }
        Product product = new Product(productDTO.getName(), productDTO.getCategory(), Integer.parseInt(productDTO.getPrice()));
        Product createdProduct = productRepo.create(product);
        if (createdProduct == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name already exist");
        }
        return createdProduct;
    }

    @GetMapping
    public ArrayList<Product> readAll() {
        return productRepo.read();
    }

    @GetMapping("/{category}")
    public ArrayList<Product> readCategory(@PathVariable String category) {
        ArrayList<Product> availiableProducts = productRepo.read(category);
        if (availiableProducts.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return availiableProducts;
    }

    @GetMapping("/{id}")
    public Product readOne(@PathVariable Integer id) {
        Product readProduct = productRepo.read(id);
        if (readProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return readProduct;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        try {
            Integer.parseInt(productDTO.getPrice());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be integer");
        }
        Product product = new Product(productDTO.getName(), productDTO.getCategory(), Integer.parseInt(productDTO.getPrice()));
        Product updatedProduct = productRepo.update(id, product);
        if (updatedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Integer id) {
        Product deletedProduct = productRepo.delete(id);
        if (deletedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return deletedProduct;
    }

}
