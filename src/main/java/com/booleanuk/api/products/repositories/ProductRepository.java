package com.booleanuk.api.products.repositories;

import com.booleanuk.api.exceptions.NoProductFound;
import com.booleanuk.api.exceptions.ProductNameAlreadyExist;
import com.booleanuk.api.products.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    private int idCounter = 4;
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>() {
            {
                add(new Product(1,"How to build API's", "Book", 1500));
                add(new Product(2,"Hello World", "Book", 42));
                add(new Product(3,"Book #3", "Book", 350));
            }
        };
    }

    // Create
    public Product create(Product product) {

        // Check if product name already exist.
        Product productName = this.products.stream().
                filter(p -> p.getName().equals(product.getName())).
                findFirst().orElse(null);

        if (productName != null) {
            throw new ProductNameAlreadyExist("Product with this name already exist.");
        }

        Product newProduct = new Product(
                this.idCounter++,
                product.getName(), product.getCategory(), product.getPrice());

        this.products.add(newProduct);
        return newProduct;
    }

    //Get all
    public List<Product> getAll(String category){
        if (category == null) {
            return this.products;
        }

        // IntelliJ collapsed loop to stream
        List<Product> list = this.products.stream().
                filter(p -> p.getCategory().equals(category)).
                collect(Collectors.toList());

        // Check if any products exist
        if (list.isEmpty()) {
           throw new NoProductFound("No products found for '"+category+"'");
        }
        return list;
    }

    // Get specific
    public Product getSpecific(int id) {
        Product product = products.stream().
                filter(p -> p.getId() == id).
                findFirst().orElse(null);

        if (product == null) {
            throw new NoProductFound("No product with this id");
        }
        return product;
    }

    // Update
    public Product update(int id, Product product) {
        Product originalProdut = getSpecific(id);
        if (originalProdut == null) {
            throw new NoProductFound("No product with this id");
        }

        // TODO: Duplicated code, should make this to a function
        // Check if product name already exist.
        Product productName = this.products.stream().
                filter(p -> p.getName().equals(product.getName())).
                findFirst().orElse(null);

        if (productName != null) {
            throw new ProductNameAlreadyExist("Product with this name already exist.");
        }

        int index = this.products.indexOf(originalProdut);
        Product updatedProduct = new Product(
                originalProdut.getId(),
                product.getName(), product.getCategory(), product.getPrice()
        );
        this.products.set(index, updatedProduct);
        return updatedProduct;
    }

    // Delete
    public Product delete(int id) {
        Product product = getSpecific(id);
        if (product == null) {
            throw new NoProductFound("No product with this id");
        }
        this.products.remove(product);
        return product;
    }

}
