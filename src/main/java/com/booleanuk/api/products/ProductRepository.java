package com.booleanuk.api.products;

import com.booleanuk.api.bagels.Bagel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public Product create(String name,String category ,int price) {
        Product product = new Product(this.idCounter++, category, category,price);
        this.data.add(product);
        return product;
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
    public Product findByName(String name) {
        return this.data.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);

//        for (Product p :this.data) {
//            if (p.getName().equals(name)){
//                return p;
//            }
//        }
//        return null;
    }
    public List<Product> findByCategory(String category) {
        if (!category.equals("")){
        return this.data.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());}
        return this.data;
    }
    public Product update(Product product) {

        var productToUpdate = this.data.stream()
                .filter(p -> p.getId() == product.getId())
                .findFirst()
                .orElseThrow();
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());

        return productToUpdate;
    }
    public Product delete(int id) {
        var productToDelete = this.data.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();

        this.data.remove(productToDelete);
        return productToDelete;
    }
}
