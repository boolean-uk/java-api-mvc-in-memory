package com.booleanuk.api.models.repositories;
import com.booleanuk.api.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepository {
    private List<Product> products;

    public ProductsRepository() {
        this.products = new ArrayList<>();

        products.add(new Product("Milk", "Dary", 2));
        products.add(new Product("Mars bar", "Candy", 1));
    }

    public Product create(Product newProduct) {
        boolean productExists = isProductInList(newProduct);

        if (productExists) {
         throw new IllegalArgumentException();
        }

        Product theProduct = new Product(newProduct.getName(), newProduct.getCategory(), newProduct.getPrice());
        this.products.add(theProduct);
        return theProduct;
    }

    private boolean isProductInList(Product newProduct) {
        return this.products.stream()
                .anyMatch(product -> product.getName().equals(newProduct.getName()));
    }

    public List<Product> getAll(String category) {
        if (category == null || category.isEmpty()) {
            return this.products;
        }
        List<Product> filteredProducts = new ArrayList<>();
        String lowerCaseCategory = category.toLowerCase();

        for (Product product : this.products) {
            if (product.getCategory().toLowerCase().equals(lowerCaseCategory)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts.isEmpty() ? null : filteredProducts;
    }

    public Product getOne(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product update(int id, Product product) {
        Product productToUpdate = getOne(id);
        boolean productExists = isProductInList(product);

        if (productExists) {
            throw new IllegalArgumentException();
        } else {
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productToUpdate;
        }
    }

    public Product remove(int id) {
        Product productToRemove = getOne(id);
        products.remove(productToRemove);
        return productToRemove;
    }
}
