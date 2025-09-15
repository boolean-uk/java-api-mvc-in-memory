package com.booleanuk.api.Product;

import java.util.*;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public ProductRepository(){
        this.data.add(new Product(0, "Eple", "Frukt", 10));
    }


    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category, price);
        this.data.add(product);
        return product;
    }

    public List<Product> findAll() { return this.data; }

    public Product find (int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product deleteProduct(int id){
        Product product = this.find(id);
        if (product == null) return null;
        this.data.remove(product);
        return product;
    }

    public Product updateProduct(int id, Product product){
        Product product1 = this.find(id);
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());
        return product1;
    }
}
