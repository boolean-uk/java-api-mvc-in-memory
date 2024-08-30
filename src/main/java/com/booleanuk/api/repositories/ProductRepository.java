package com.booleanuk.api.repositories;

import com.booleanuk.api.product.Product;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        this.products=new ArrayList<>();
        this.products.add(new Product("Coffler", "Cup", 9001));
        this.products.add(new Product("Well of Ascension", "Book", 169));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id){

        for (Product p:this.products){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public Product findNamedProduct(String name){
        for (Product p:this.products){
            if(p.getName()==name){
                return p;
            }
        }
        return null;
    }

    public Product add(Product product){
        for (Product p: products){
            if (p.getName()==product.getName()){
                return null;
            }
        }
        products.add(product);
        return product;
    }

    public Product remove(int id){
        for (Product p:this.products){
            if(p.getId()==id){
                Product ret=new Product(p.getName(), p.getCategory(), p.getPrice());
                ret.setId(id);
                products.remove(p);
                return ret;
            }
        }
        return null;
    }



}
