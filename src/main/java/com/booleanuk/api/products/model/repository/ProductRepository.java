package com.booleanuk.api.products.model.repository;

import com.booleanuk.api.products.controller.dto.ProductDto;
import com.booleanuk.api.products.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    // Find all
    public List<Product> findAll() {
        return products;
    }

    // Find all by category
    public List<Product> findAllByCategory(String category) throws Exception {
        List<Product> filtered = findAll().stream()
                .filter(p -> category.equalsIgnoreCase(p.getCategory()))
                .toList();

        if (filtered.isEmpty()) {
            throw new Exception("No products in the provided category were found");
        }

        return filtered;
    }

    // Find by id
    public Product findById(int id) throws Exception {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("Not found"));
    }

    // Create
    public Product create(Product product) throws Exception {
        List<Product> filtered = products
                .stream()
                .filter(p -> p.getName().equals(product.getName()))
                .toList();

        if (filtered.isEmpty()) {
            Product newProduct = new Product(product.getName(), product.getCategory(), product.getPrice());
            products.add(newProduct);
            return product;
        }

        throw new Exception("Product with provided name already exists");
    }

    // Update by id
    public Product updateById(int id, ProductDto body) throws Exception {
        // Will throw if product with id not found
        Product currentProduct = findById(id);

        // Check if name already in other product
        List<Product> filtered = products.stream()
                .filter(p -> p.getName().equals(body.getName()))
                .toList();
        if (!filtered.isEmpty()) {
            throw new Exception("Product with provided name already exists");
        }
        currentProduct.setName(body.getName());
        currentProduct.setCategory(body.getCategory());
        currentProduct.setPrice(body.getPrice());
        return currentProduct;
    }

    // Delete by id
    public Product deleteById(int id) throws Exception {
        Product productToDelete = findById(id);
        products = products.stream().filter(p -> p.getId() != id).toList();
        return productToDelete;
    }

}
