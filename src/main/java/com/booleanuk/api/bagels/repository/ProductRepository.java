package com.booleanuk.api.bagels.repository;

import com.booleanuk.api.bagels.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product("Book Title", 1500, "Book"));
        this.products.add(new Product("Shirt", 2000, "Clothing"));
    }

    public List<Product> getAll() {
        return this.products;
    }

    public Optional<Product> create(Product product) {
        if (products.stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()))) {
            return Optional.empty();
        }
        products.add(product);
        return Optional.of(product);
    }

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Product> update(int id, Product updatedProduct) {
        return getProductById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            return product;
        });
    }

    public Optional<Product> delete(int id) {
        Optional<Product> product = getProductById(id);
        product.ifPresent(products::remove);
        return product;
    }
}
