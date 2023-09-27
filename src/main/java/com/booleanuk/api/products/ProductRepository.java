package com.booleanuk.api.products;

import java.util.ArrayList;

public class ProductRepository {
    static int productID = 1;
    private final ArrayList<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();
    }

    public ArrayList<Product> findAll(){
        return this.products.isEmpty()? null: this.products;
    }

    public Product find(int id){
        return this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Product create(Product product){
        Product toBeCreated = new Product(productID++, product.getName(), product.getCategory(), product.getPrice());
        this.products.add(toBeCreated);
        return toBeCreated;
    }

    public Product update(int id, Product product){
        Product toBeUpdated = this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
        if (toBeUpdated != null){
            toBeUpdated.setName(product.getName());
            toBeUpdated.setCategory(product.getCategory());
            toBeUpdated.setPrice(product.getPrice());
        }
        return toBeUpdated;
    }

    public Product delete(int id){
        Product toBeDeleted = this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
        if (toBeDeleted != null){
            this.products.remove(toBeDeleted);
        }
        return toBeDeleted;
    }
}
