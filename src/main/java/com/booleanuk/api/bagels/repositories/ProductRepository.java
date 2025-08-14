package com.booleanuk.api.bagels.repositories;

import com.booleanuk.api.bagels.models.Product;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<Product>();

        this.products.add(new Product("Asus", "Laptop", 1200));
        this.products.add(new Product("LG", "TV", 600));
    }

    public ArrayList<Product> findAll(){
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setId(i);
        }
        return this.products;
    }

    public Product findByID(int id) {
        if(id >= 0 && id<products.size()){
            products.get(id).setId(id);
            return products.get(id);
        }
        return null;
    }

    public void addProduct(Product product){
        product.setId(products.size() - 1);
        products.add(product);
    }

    public boolean updateProduct(int id, Product product){
        if(id >= 0 && id<products.size()){
            product.setId(id);
            products.set(id, product);
            return true;
        }
        return false;
    }

    public Product delete(int id) {
        if (id >= 0 && id<products.size()) {
            return this.products.remove(id);
        }
        return null;
    }
}
