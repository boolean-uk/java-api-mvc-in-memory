package com.booleanuk.api.products.repository;

import com.booleanuk.api.products.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository(){
        this.productList = new ArrayList<>();
    }

    public List<Product> getAll(){
        if(this.productList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of provided category were found");
        }
        return this.productList;
    }

    public List<Product> getAll(String category){
        List<Product> categorizedProducts = new ArrayList<>();
        for (Product p : this.productList){
            if(Objects.equals(p.getCategory(), category)){
                categorizedProducts.add(p);
            }
        }
        if(categorizedProducts.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of provided category were found");
        }
        return categorizedProducts;
    }

    public Product create(Product product){
        for (Product p : this.productList){
            if(Objects.equals(p.getName(), product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
            }
        }
        this.productList.add(product);
        return product;
    }

    public Product update(int id, Product product){
        for(Product p : this.productList){
            if(Objects.equals(p.getName(), product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Product with provided name already exists");
            }
        }

        for (Product p : this.productList){
            if(p.getId() == id){
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setCategory(product.getCategory());
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product get(int id){
        for (Product product : this.productList){
            if(product.getId() == id){
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product delete(int id){
        for (Product product : this.productList){
            if(product.getId() == id){
                this.productList.remove(product);
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

}
