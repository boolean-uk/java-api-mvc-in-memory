package com.booleanuk.api.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRepo {
    private ArrayList<Product> products;

    public ProductRepo(){
        this.products = new ArrayList<>();
        this.products.add(new Product("Norway's greatest hits", "CD", 2));
        this.products.add(new Product("Nina Jirachi - Fk My Computer", "Vinyl", 999));
    }

    public List<Product> getAll(){
        return products;
    }

    public List<Product> getByCategory(String category){
        return products.stream().filter(product -> Objects.equals(category, product.getCategory())).toList();
    }

    public Product getForID(int id){
        Product product = null;
        try {
            product = this.products.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return product;
    }

    public Product update(int id, Product product){
        Product productToUpdate = null;
        try {
            productToUpdate = this.products.get(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }

        productToUpdate.update(product, id);
        return productToUpdate;
    }

    public void delete(int id){
        try {
            this.products.remove(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    public Product create(Product product){
        this.products.add(product);
        return this.getForID(product.getId());
    }
}
