package com.booleanuk.api.products;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private int idCounter = 1;

    public List<Product> getAll(String category) {
        if (category == null || category.isEmpty()) {
            return new ArrayList<>(products);
        } else {
            List<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory().equalsIgnoreCase(category)) {
                    filteredProducts.add(product);
                }
            }
            return filteredProducts;
        }
    }

    public Product getOne(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(Product product) {
        product.setId(idCounter++);
        products.add(product);
        return product;
    }


    public Product edit(int id, Product product) {
        Optional<Product> optionalProduct = products.stream().filter(p -> p.getId() == id).findFirst();
        optionalProduct.ifPresent(p -> {
            p.setName(product.getName());
            p.setCategory(product.getCategory());
            p.setPrice(product.getPrice());
        });
        return optionalProduct.orElse(null);
    }

    public void delete(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public boolean exists(String productName) {
        return products.stream().anyMatch(product -> product.getName().equals(productName));
    }

    public boolean existsById(int id) {
        return products.stream().anyMatch(product -> product.getId() == id);
    }
}
