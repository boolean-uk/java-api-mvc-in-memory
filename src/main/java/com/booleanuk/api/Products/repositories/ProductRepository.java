package com.booleanuk.api.Products.repositories;

import com.booleanuk.api.Products.models.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> productList = new ArrayList<>();


    public ProductRepository(){

        productList.add(new Product("HP", "Laptop", 5000));
        productList.add(new Product("ThinkPad", "Laptop", 25000));
    }


    public List<Product> getAllProducts() {
        return this.productList;
    }


    public Product getProduct(int id){

        for(Product p : this.productList) {
            if (p.getID()==id)
                return p;
        }

        return null;
    }

    public Product addProduct(Product product){

        if(product==null)
            return null;

        this.productList.add(product);

        return product;
    }

    public Product delete( int id){


        Product p = getProduct(id);
        this.productList.remove(p);
        return p;
    }

}
