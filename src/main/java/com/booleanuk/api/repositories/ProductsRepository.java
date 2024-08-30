package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.models.ProductNotFoundException;

import java.util.ArrayList;

public class ProductsRepository {
    private ArrayList<Product> products;

    public ProductsRepository() {
        this.products = new ArrayList<>();
        this.products.add(new Product("Shampoo", "Health", 20));
        this.products.add(new Product("Bread", "Food", 30));
        this.products.add(new Product("Yoghurt", "Food", 15));

    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public Product createProduct(Product newProduct) {
        if (products.stream().anyMatch(p -> p.getName().equals(newProduct.getName()))) {
            throw new ProductNotFoundException.ProductAlreadyExistsException("Product with name " + newProduct.getName() + " already exists");
        }
        Product product = new Product(newProduct.getName(), newProduct.getCategory(), newProduct.getPrice());
        products.add(product);
        return product;
    }

    public Product getOne(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    public Product updateProduct(int id, Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                if (products.stream().anyMatch(p -> p.getName().equals(updatedProduct.getName()) && p.getId() != id)) {
                    throw new ProductNotFoundException.ProductAlreadyExistsException("Product with name " + updatedProduct.getName() + " already exists");
                }
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }

    public Boolean deleteProduct(int id) {
        ArrayList<Product> allProducts = getAll();
        allProducts.removeIf(product -> product.getId() == id);
        return null;
    }




}
