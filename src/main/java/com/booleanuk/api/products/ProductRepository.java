package com.booleanuk.api.products;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {}

    public Product createProduct(Product product) throws Exception {
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                throw new Exception("Product with provided name already exists.");
            }
        }

        product.setId(idCounter++);
        this.productList.add(product);
        return product;
    }

    public List<Product> getAllProducts(String category) throws Exception {
        List<Product> list = productList.stream()
                                        .filter(product -> product.getCategory().equalsIgnoreCase(category))
                                        .toList();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("No products of the provided category were found.");
        }
    }

    public Product getSingleProduct(int id) {
        return productList.stream()
                .filter(product ->  product.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public Product updateProduct(int id, Product body) {
        Product product = productList.stream()
                                    .filter(p -> p.getId() == id)
                                    .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(body.getName()))
                throw new IllegalArgumentException("Product with provided name already exists.");
        }

        product.setName(body.getName());
        product.setCategory(body.getCategory());
        product.setPrice(body.getPrice());
        return product;
    }

    public void deleteProduct(int id) {
        Product product = productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        productList.remove(product);
    }
}
