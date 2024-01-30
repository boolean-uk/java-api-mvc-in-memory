package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ProductRepository {
    private final List<Product> data = new ArrayList<>();

    public Product create(String name, String category, int price) {
        Product product = new Product(UUID.randomUUID(), name, category, price);
        this.data.add(product);
        log.info("create: Added new product: " + product);
        return product;
    }

    public List<Product> findAll() {
        return this.data;
    }

    public List<Product> findAllByCategory(String category) {
        return this.data.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Product find(UUID id) {
        return this.data.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public boolean containsName(String name) {
        return this.data.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    public Product update(UUID id, Product newProduct) {
        Product productToUpdate = this.data.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();

        log.info("update: Updating " + productToUpdate + " with values from " + newProduct);
        productToUpdate.setName(newProduct.getName());
        productToUpdate.setCategory(newProduct.getCategory());
        productToUpdate.setPrice(newProduct.getPrice());

        return find(id);
    }

    public Product delete(UUID id) {
        Product productToRemove = this.data.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();

        log.info("delete: Deleting " + productToRemove);
        this.data.remove(productToRemove);
        return productToRemove;
    }
}
