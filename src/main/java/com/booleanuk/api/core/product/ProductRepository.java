package com.booleanuk.api.core.product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;

    ProductRepository() {
        this.products = new ArrayList<>();

    }

    public Product add(Product product) {
       products.add(product);
       return product;
    }

    public ArrayList<Product> getAll() {
        return products;
    }

    public Product getOne(int id) {
       for(Product product : products) {
           if (product.getId() == id) {
               return product;
           }
       }
       return null;
    }

    public Product update(Product product, int id) {
        for (Product value : products) {
            if(value.getId() == id) {
                value.setName(product.getName());
                value.setCategory(product.getCategory());
                value.setPrice(product.getPrice());
                return product;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id) {
                return products.remove(i);
            }
        }
        return null;
    }


}
