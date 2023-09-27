package com.booleanuk.api.products;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductRepository {
    static int productID = 1;
    private final ArrayList<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();
    }

    public ArrayList<Product> findAll(){
        return this.products.isEmpty()? null: this.products;
    }

    public ArrayList<Product> findByCategory(String category){
        return this.products.stream()
                .filter(thisProduct -> thisProduct.getCategory().equals(category))
                .collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList<Product>::new), c -> !c.isEmpty()? c: null));
    }

    public Product find(int id){
        return this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
    }

    public Product create(Product product){
        Product existingProduct = this.products.stream()
                .filter(thisProduct -> thisProduct.getName().equals(product.getName()))
                .findAny()
                .orElse(null);
        if (existingProduct == null){
            Product toBeCreated = new Product(productID++, product.getName(), product.getCategory(), product.getPrice());
            this.products.add(toBeCreated);
            return toBeCreated;
        }
        return null;
    }

    public Product update(int id, Product product) throws InstanceAlreadyExistsException{
        Product toBeUpdated = this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
        if (toBeUpdated != null){
            Product existingProduct = this.products.stream()
                    .filter(thisProduct -> thisProduct.getName().equals(product.getName()))
                    .findAny()
                    .orElse(null);
            if (existingProduct == null){
                toBeUpdated.setName(product.getName());
                toBeUpdated.setCategory(product.getCategory());
                toBeUpdated.setPrice(product.getPrice());
            } else {
                throw new InstanceAlreadyExistsException("Product with provided name already exists.");
            }
        }
        return toBeUpdated;
    }

    public Product delete(int id){
        Product toBeDeleted = this.products.stream()
                .filter(thisProduct -> thisProduct.getId() == id)
                .findAny()
                .orElse(null);
        if (toBeDeleted != null){
            this.products.remove(toBeDeleted);
        }
        return toBeDeleted;
    }
}
