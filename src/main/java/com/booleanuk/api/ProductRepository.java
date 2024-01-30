package com.booleanuk.api;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();
    }

    public List<Product> getProducts(){
        return products;
    }

    public Product getProduct(int id){
        for (Product product : products){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public boolean addProduct(Product product){
        if (getProductByName(product.getName()) != null){
            return false;
        }

        products.add(product);

        return true;
    }

    public Product deleteProduct(int id){
        int index = products.indexOf(getProduct(id));

        return products.remove(index);
    }

    public Product getProductByName(String name){
        for (Product product : products){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
}
