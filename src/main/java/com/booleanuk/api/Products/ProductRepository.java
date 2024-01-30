package com.booleanuk.api.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product createProduct(Product createProduct) {
        this.products.add(createProduct);
        return createProduct;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getSpecificProduct(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product existingProduct = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setPrice(updatedProduct.getPrice());
        }

        return existingProduct;
    }
    public Product deleteProduct(int id) {
        Product productToDelete = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);

        if (productToDelete != null) {
            products.remove(productToDelete);
        }

        return productToDelete;
    }


}
