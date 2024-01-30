package com.booleanuk.api.products;

import com.booleanuk.api.bagels.Bagel;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> data = new ArrayList<>();

    public ProductRepository () {
        data.add(new Product("Car model", "Toy", 15));
    }

    public void create(String name, String category, int price) {
        Product product = new Product(name, category, price);
        this.data.add(product);
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id){
                return data.get(i);
            }
        }
        return null;
    }

    public Product update(int id, Product updatedProduct) {
        Product productToUpdate = this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setPrice(updatedProduct.getPrice());

        }
        return updatedProduct;
    }

    public Product delete(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id){
                data.remove(i);
            }
        }
        return null;
    }

}
