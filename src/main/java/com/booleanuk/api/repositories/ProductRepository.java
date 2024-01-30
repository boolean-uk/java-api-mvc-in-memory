package com.booleanuk.api.repositories;

import com.booleanuk.api.requests.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Sofa", "Furniture", 2499));
        this.products.add(new Product("Leather recliner", "Furniture", 999));
    }

    public Product create(Product product) {
        boolean created = this.products.add(product);
        if (created) {
            return product;
        }
        return null;
    }

    public List<Product> findAll() {
        return new ArrayList<>(this.products);
    }

    public List<Product> findByCategory(String category) {
        return this.products.stream().filter
                (product -> product.getType().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Optional<Product> find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    public Product delete(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                return product;
            }
        }
        return null;
    }

    public Product modify(int id, Product updatedProduct) {
        Optional<Product> optionalProductToUpdate = this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();

        if (optionalProductToUpdate.isPresent()) {
            Product productToUpdate = optionalProductToUpdate.get();

            if (!productToUpdate.getName().equalsIgnoreCase(updatedProduct.getName()) && productWithNameExists(updatedProduct.getName())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "A product with the provided name already exists");
            }

            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setType(updatedProduct.getType());
            productToUpdate.setPrice(updatedProduct.getPrice());

            return productToUpdate;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A product with this id does not exist");
        }
    }

    public boolean productWithNameExists(String name) {
        return this.products.stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }
}
