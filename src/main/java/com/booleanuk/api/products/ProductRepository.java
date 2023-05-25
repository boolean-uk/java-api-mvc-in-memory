package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>() {{
            add(new Product(0, "apple", "fruit", 10));
            add(new Product(1, "beer", "drinks", 20));
        }};
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Optional<Product> getProduct(int id) {
        return this.products.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void putProduct(int id, Product product) {
        Optional<Product> searchedProduct = this.products.stream().filter(p -> p.getId() == id).findFirst();
        if (searchedProduct.isPresent()) {
            searchedProduct.get().setName(product.getName());
            searchedProduct.get().setCategory(product.getCategory());
            searchedProduct.get().setPrice(product.getPrice());
        }
    }

    public Product postProduct(Product product) {
        product.setId(this.products.get(this.products.size() - 1).getId() + 1);
        this.products.add(product);
        return this.products.get(this.products.size() - 1);
    }

    public Product deleteProduct(int id) {
        Optional<Product> searchedProduct = this.products.stream().filter(p -> p.getId() == id).findFirst();
        if (searchedProduct.isEmpty()) return null;
        this.products.remove(searchedProduct.get());
        return searchedProduct.get();
    }
}
