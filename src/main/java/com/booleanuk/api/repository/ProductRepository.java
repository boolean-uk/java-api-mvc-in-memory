package com.booleanuk.api.repository;

import com.booleanuk.api.exceptions.NoProductsInCategoryException;
import com.booleanuk.api.exceptions.ProductNameExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;
import com.booleanuk.api.model.Product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product add(Product newProduct) {
        // Check if name exists, if it does return null
        for (Product p : this.products)
            if (p.getName().equals(newProduct.getName()))
                throw new ProductNameExistsException("Given product name already exists.");

        Product actualNewProduct = new Product(newProduct.getName(), newProduct.getCategory(), newProduct.getPrice());
        this.products.add(actualNewProduct);
        return actualNewProduct;
    }

    public ArrayList<Product> getAll(String category) {
        if (category == null || category.isEmpty()) {
            return this.products;
        }

        category = category.toLowerCase();
        ArrayList<Product> toReturn = new ArrayList<>();
        for (Product p : this.products) {
            if (p.getCategory().toLowerCase().equals(category)) toReturn.add(p);
        }

        // Check if no products with the given category were found
        if (toReturn.isEmpty()) throw new NoProductsInCategoryException("No products exist in the given category.");

        return toReturn;
    }

    public Product getOne(int id) {
        Product product = null;
        for (Product p : this.products) if (p.getId() == id) return p;
        throw new ProductNotFoundException("Given product ID does not exist.");
    }

    public Product update(int id, Product newProduct) {
        Product actualProduct = null;
        for (Product p : this.products) if (p.getId() == id) actualProduct = p;

        // Check if given product id exists
        if (actualProduct == null) throw new ProductNotFoundException("Product ID not found.");

        // Check if given product name exists
        for (Product p : this.products)
            if (p.getName().equals(newProduct.getName()))
                throw new ProductNameExistsException("Given product name already exists.");

        actualProduct.setCategory(newProduct.getCategory());
        actualProduct.setName(newProduct.getName());
        actualProduct.setPrice(newProduct.getPrice());

        return actualProduct;
    }

    public Product remove(int id) {
        Product toReturn = null;
        for (int i = 0; i < this.products.size(); ++i) {
            if (this.products.get(i).getId() == id) {
                toReturn = this.products.get(i);
                this.products.remove(i);
                return toReturn;
            }
        }
        throw new ProductNotFoundException("Given product ID does not exist.");
    }

}
