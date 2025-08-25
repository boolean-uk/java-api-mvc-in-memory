package com.booleanuk.api.product.model.repository;

import com.booleanuk.api.product.controller.Dto.ProductCreateDto;
import com.booleanuk.api.product.model.pojo.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Spoon", "Kitchen", 100));
        this.products.add(new Product("Laptop", "Electronics", 10000));
        this.products.add(new Product("Book", "Education", 150));

    }

    public List<Product> getProducts(String category) {
        if (category == null || category.isBlank()) {
            return this.products;
        }
        List<Product> result = new ArrayList<>();
        for (Product p : products){
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }

        return result;
    }

    public Product getOne(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product not found."
        );
    }

    public void createProduct(Product product) {
        for (Product p : this.products) {
            if (p.getName().equalsIgnoreCase((product.getName()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }

        }
       products.add(product);
    }

    public Product updateProduct(int id, ProductCreateDto body) {
        for (Product p : this.products) {
            if (p.getId() == id) {
                p.setName(body.getName());
                p.setPrice(body.getPrice());
                p.setCategory(body.getCategory());
                return p;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
    }

    public Product deleteProduct(int id) {
        for (Product p : this.products){
            if (p.getId() == id) {
                this.products.remove(p);
                return p;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}
