package com.booleanuk.api.product.repositories;

import com.booleanuk.api.product.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> getAll(){
        return this.products;
    }

    public boolean checkNameConflict(Product product){
        for(Product curr : this.products){
            if(curr.getName().equals(product.getName())){
                return true;
            }
        }
        return false;
    }

    public Product add(Product product){ //Void for now, would return db return if connected to db
        this.products.add(product);
        return product;
    }

    public Product getById(int id){
        for(Product product : this.products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Product removeProduct(int id){
        Product removeProd = this.getById(id);
        this.products.remove(removeProd);
        return removeProd; //Return null if not found
    }

    public List<Product> getAllByCategory(String cat){
        List<Product> found = new ArrayList<>();
        for(Product curr : this.products){
            if(cat.equals(curr.getCategory())){
                found.add(curr);
            }
        }
        return found;

    }
}
