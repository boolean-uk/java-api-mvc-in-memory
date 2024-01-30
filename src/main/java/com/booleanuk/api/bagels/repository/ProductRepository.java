package com.booleanuk.api.bagels.repository;

import com.booleanuk.api.bagels.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;
    public ProductRepository(){
        this.products = new ArrayList<>();

        this.products.add(new Product("Ateeb",10,"book"));
        this.products.add(new Product("Hassan",20,"clothing"));
        this.products.add(new Product("Noha",30,"sport"));
    }
    public List<Product> getAll(){
        return  this.products;
    }
    public Product created(Product author){
        this.products.add(author);
        return author;
    }
    public Product getOneAuthor( int id){
        if ( id < this.products.size()){
            return this.products.get(id);
        }
        return null;
    }
    public Product update( int id, Product author){
        if (id < this.products.size()){
            this.products.get(id).setType(author.getType());
            this.products.get(id).setPrice(author.getPrice());
            this.products.get(id).setCategory(author.getCategory());
            return this.products.get(id);
        }
        return null;
    }
    public Product delete( int id){
        if (id < this.products.size()){
            return this.products.remove(id);
        }
        return null;
    }
}
