package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductRepository {

    private ArrayList<Product> products;
    private int id;

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.id = 1;
    }

    public Product findOne(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Product findOne(String name){
        return this.products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Product> findAll(String category) {
        if(category != null && !category.isEmpty()){
            return (ArrayList<Product>) this.products.stream()
                    .filter(product -> product.getCategory().equals(Character.toUpperCase(category.charAt(0))
                            + category.substring(1)))
                    .collect(Collectors.toList());
        }
        else {
            return this.products;
        }

    }

    public Product createOne(Product product) {
        Product temp = this.products.stream()
                .filter(p -> p.getName().equals(product.getName()))
                .findFirst()
                .orElse(null);

        if(temp == null){
            product.setId(this.id++);
            this.products.add(product);
            return product;
        }
        else{
            return null;
        }

    }

    public Product updateOne(int id, Product product) {
        Product temp = this.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (temp != null) {
            if(product.getCategory() != null){
                temp.setCategory(product.getCategory());
            }
            if(product.getName() != null){
                temp.setName(product.getName());
            }
            if(product.getPrice() != 0){
                temp.setPrice(product.getPrice());
            }


        }
        return temp;
    }

    public Product deleteOne(int id) {
        Product temp = this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);

        if (temp != null) {
            this.products.remove(temp);
        }
        return temp;
    }
}
