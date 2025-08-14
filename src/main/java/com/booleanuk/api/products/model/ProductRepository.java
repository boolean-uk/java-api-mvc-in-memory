package com.booleanuk.api.products.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public ProductRepository() {
        Product product1 = new Product(this.idCounter++, "car", "utilities", 10000);
        Product product2 = new Product(this.idCounter++, "house", "utilities", 200000);
        Product product3 = new Product(this.idCounter++, "beer", "drink", 5);
        this.data.add(product1);
        this.data.add(product2);
        this.data.add(product3);
    }

    public List<Product> findAll(String category) {
        if (category != null && !category.isEmpty()){
            List<Product> ls = this.data.stream().filter(n -> n.getCategory().equals(category)).toList();
            if (ls.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
            }
            return ls;
        }
        return this.data;
    }

    public Product find(int id) {
        List<Product> product = this.data.stream()
                .filter(prod -> prod.getId() == id)
                .toList();
        if (product.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return product.getFirst();
    }

    public Product addProduct(Product product){
        if (data.stream().filter(it -> it.getName().equals(product.getName())).toList().isEmpty()){
            this.data.add(product);
            throw new ResponseStatusException(HttpStatus.CREATED, "Create a new product");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
    }

    public Product updateProduct(int id, String name){
        Product product = find(id);
        List<Product> existingProduct = data.stream().filter(it -> it.getName().equals(name)).toList();

        if (!existingProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        product.setName(name);
        throw new ResponseStatusException(HttpStatus.CREATED, "Product successfully updated.");
    }

    public boolean deleteProduct(int id){
        Product product = find(id);
        this.data.remove(product);
        throw new ResponseStatusException(HttpStatus.OK, "Product successfully deleted.");
    }
}
