package com.booleanuk.api.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepository {

    private ArrayList<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<>();

        this.products.add(new Product("Volvo c30 R-design", "Car", 100000));
        this.products.add(new Product("Nintendo", "Game console", 4000));
    }

    public ArrayList<Product> getAll(){
        return this.products;
    }

    public Product getOne(int id){
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Product updateProduct(int id, Product updatedProduct){
        for(Product product : products){
            if(product.getId() == id){
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }
        }
        return null;
    }

    public Product removeProduct(int id){
        for(Product product : products){
            if(product.getId() == id){
                products.remove(product);
                return product;
            }
        }
        return null;
    }

    public Product createProduct(Product product){
        products.add(product);
        return product;
    }

    public Boolean chechNameAvailable(String name){
        for (Product product : products){
            if(product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Boolean chechNameAvailable(String name, int id){
        for (Product product : products){
            if(product.getName().equals(name) && product.getId() != id){
                return true;
            }
        }
        return false;
    }

    public List<Product> getAll(String category) {
        List<Product> temp = new ArrayList<>();
        temp = this.products.stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
        return temp;
    }
}
