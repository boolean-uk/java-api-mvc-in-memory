package com.booleanuk.api.bagels.repositories;

import com.booleanuk.api.bagels.models.Product;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

public class ProductsRepository {
    private ArrayList<Product> data;

    public ProductsRepository(){
        this.data = new ArrayList<>();


        this.data.add(new Product("Fidget Spinner", "Toys", 200));
        this.data.add(new Product("Xbox Series X", "Gaming", 7500));
    }

    public Product create(Product insertProduct) {
        for (Product item : data){
            if (Objects.equals(item.getName(), insertProduct.getName())){
                return null;
            }
        }

        Product product = new Product(insertProduct.getName(), insertProduct.getCategory(), insertProduct.getPrice());

        this.data.add(product);
        return product;
    }

    public ArrayList<Product> findAll(String category) {
        ArrayList<Product> categoryList = new ArrayList<>();

        for (Product product : data){
            if(product.getCategory().equalsIgnoreCase(category)){
                categoryList.add(product);
            }
        }

        if (!categoryList.isEmpty()){
            return categoryList;
        }

        return this.data;
    }

    public Product find(int id){
        for (Product product : data){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Product update(int id, Product insertProduct){

        for(Product product : data){
            if (product.getId() == id && Objects.equals(product.getName(), insertProduct.getName())) {
                throw new IllegalArgumentException();
            }
            if (product.getId() == id){
                product.setName(insertProduct.getName());
                product.setCategory(insertProduct.getCategory());
                product.setPrice(insertProduct.getPrice());

                return product;
            }
        }

        return null;
    }

    public Product remove(int id){
        for (Product product : data){
            if(product.getId() == id){
                data.remove(product);

                return product;
            }
        }
        return null;
    }


}
