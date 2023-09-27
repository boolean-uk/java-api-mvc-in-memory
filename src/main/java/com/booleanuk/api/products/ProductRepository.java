package com.booleanuk.api.products;

import com.booleanuk.api.exceptions.CustomBadRequestException;
import com.booleanuk.api.exceptions.CustomNotFoundException;
import com.booleanuk.api.utils.ProductNameComparator;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ProductRepository {
    private static int idCounter = 4;
    private final Set<Product> products;

    public ProductRepository() {
        this.products = new TreeSet<>(new ProductNameComparator());
        this.products.add(new Product(1, "Apple", "fruit", 2));
        this.products.add(new Product(2, "Aubergine", "vegetable", 3));
        this.products.add(new Product(3, "Cake", "dessert", 10));
    }


    public Product create(String name, String category, int price) throws CustomNotFoundException {
        Product product = new Product(idCounter++, name, category, price);
        if (this.products.add(product)) {
            return product;
        } else {
            throw new CustomBadRequestException("Product with provided name already exists.");
        }
    }

    public Product find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Set<Product> findAll(String category) throws CustomNotFoundException {
        Stream<Product> productStream = this.products.stream();
        if (category != null && !category.isEmpty()) {
            Set<Product> filteredProducts = productStream
                    .filter(product -> product.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toSet());

            if (filteredProducts.isEmpty()) {
                throw new CustomNotFoundException("No products of the provided category were found.");
            }

            return filteredProducts;
        }
        return productStream.collect(Collectors.toSet());
    }

    public Product update(int id, String name, String category, int price) throws CustomNotFoundException {
        Product product;
        try {
            product = this.find(id);
        } catch (NoSuchElementException e) {
            throw new CustomNotFoundException("Product not found.");
        }
        if (this.products.stream().anyMatch(aProduct -> aProduct.getName().equalsIgnoreCase(name))) {
            throw new CustomBadRequestException("Product with the provided name already exists.");
        }

        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public Product delete(int id) {
        Product product;
        try {
            product = this.find(id);
        } catch (NoSuchElementException e) {
            throw new CustomNotFoundException("Product not found.");
        }
        this.products.remove(product);
        return product;
    }
}
