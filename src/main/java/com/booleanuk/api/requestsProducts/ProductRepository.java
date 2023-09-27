package com.booleanuk.api.requestsProducts;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("How to build APIs", "Book", 1500));
        this.products.add(new Product("Titanic", "DVD movie", 1000));
        this.products.add(new Product("World of Hans Zimmer", "CD", 1200));
    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public ArrayList<Product> getByCategory(String category) {
        ArrayList<Product> sublistOfProducts = new ArrayList<>();
        for (Product product : this.products) {
            System.out.println(category);
            if (product.getCategory().equalsIgnoreCase(category)) {
                sublistOfProducts.add(product);
            }
        }
        return sublistOfProducts;
    }

    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(name, category, price);
        for (Product existingProduct : this.products) {
            if (existingProduct.getName().equalsIgnoreCase(name)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with provided name already exists");
            }
        }
        this.products.add(product);
        return product;
    }

    public Product update(int id, Product product) {
        for (Product anotherProduct : this.products) {
            if (anotherProduct.getName().equalsIgnoreCase(product.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }
        for (Product anotherProduct : this.products) {
            if (anotherProduct.getId() == id) {
                anotherProduct.setName((product.getName()));
                anotherProduct.setCategory((product.getCategory()));
                anotherProduct.setPrice((product.getPrice()));
                return anotherProduct;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product deleteProduct(int id) {
        Product productToDelete = null;

        for(Product justAnotherProduct : this.products) {
        if (justAnotherProduct.getId() == id) {
            productToDelete = justAnotherProduct;
            break;
        }
    }
        if (productToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        this.products.remove(productToDelete);
        return productToDelete;
    }
}

