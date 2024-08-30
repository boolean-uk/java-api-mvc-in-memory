package com.booleanuk.api.repositories;

import com.booleanuk.api.exceptions.ProductAlreadyExistsException;
import com.booleanuk.api.exceptions.ProductNotFoundException;
import com.booleanuk.api.models.Product;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<>();

        this.products.add(new Product("placeholderName", "placeholderCategory", 1));
    }

    public ArrayList<Product> getAll(){
        return this.products;
    }

    public ArrayList<Product> getCategory(String category){
        ArrayList<Product> productsOfCategory = new ArrayList<>();
        for(Product product : this.products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsOfCategory.add(product);
            }
        }
        if(productsOfCategory.isEmpty()){
            throw new ProductNotFoundException("Not found.");
        }
        return productsOfCategory;
    }

    public Product getOne(int id){
        for(Product product : this.products){
            if (product.getId() == id){
                return product;
            }
        }
        throw new ProductNotFoundException("Not found.");
    }

    public Product create(Product product){
        for(Product p : this.products){
            if(p.getName().equals(product.getName())){
                throw new ProductAlreadyExistsException(HttpStatus.BAD_REQUEST, "Product already exists.");
            }
        }
        this.products.add(product);
        return product;
    }

    public Product update(int id, Product product) throws ProductNotFoundException {
        for(Product p : this.products){
            if(p.getName().equals(product.getName())){
                throw new ProductAlreadyExistsException(HttpStatus.BAD_REQUEST, "Product name already exists!");
            }
        }
        Product productToUpdate;
        try {
            productToUpdate = this.getOne(id);
        } catch (NoSuchElementException e){
            throw new ProductNotFoundException("No product with that ID found!");
        }
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productToUpdate;
    }

    public Product delete(int id){
        for(int i = 0; i < this.products.size(); ++i){
            if (this.products.get(i).getId() == id){
                return this.products.remove(i);
            }
        }
        throw new ProductNotFoundException("Not found.");
    }

}
