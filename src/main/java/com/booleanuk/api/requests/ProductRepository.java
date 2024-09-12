package com.booleanuk.api.requests;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product create(Product product){
        this.products.add(product);
        return product;
    }

    public ArrayList<Product> getAll(){
        return this.products;
    }

    public Product getOne(int id){
        for (Product p : this.products){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> getAll(String category){
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product p : this.products) {
            if (p.getCategory().equalsIgnoreCase(category)){
                filteredProducts.add(p);
                return filteredProducts;
            }
        }
        return null;
    }

    public Product update(int id, Product product){
        for (Product p : this.products){
            if (p.getId() == id){
                p.setName(product.getName());
                p.setCategory(product.getCategory());
                p.setPrice(product.getPrice());
                return p;
            }
        }
        return null;
    }

    public Product delete(int id){
        for (Product p : this.products){
            if (p.getId() == id){
                this.products.remove(p);
                return p;
            }
        }
        return null;
    }
}
