package com.booleanuk.api.products;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product create(Product product){
        for(Product product1 : products){
            if(product.getName().equals(product1.getName())){
                return null;
            }
        }
        this.products.add(product);
        return product;
    }
    public ArrayList<Product> getAll(){
        if(products.size() != 0){
            return this.products;
        }
        return null;
    }

    public Product getOne(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product update(int id,Product product) {
        for(Product product1 : products){
            if (product1.getId() == id){
                product1.setId(id);
                product1.setName(product.getName());
                product1.setCategory(product.getCategory());
                product1.setPrice(product.getPrice());
                return product1;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for(Product product : products){
            if (product.getId() == id){
                products.remove(product);
                return product;
            }
        }
        return null;
    }
}
