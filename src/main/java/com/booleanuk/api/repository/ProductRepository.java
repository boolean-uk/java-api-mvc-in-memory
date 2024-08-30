package com.booleanuk.api.repository;

import com.booleanuk.api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        products = new ArrayList<>();

        products.add(new Product("Name 1", "Film", 100));
        products.add(new Product("Name 2", "Book", 200));
        products.add(new Product("Name 3", "Film", 300));
    }


    public ArrayList<Product> getAll(){
        return products;
    }

    public ArrayList<Product> getAll(String cat) {
        ArrayList<Product> getCat = new ArrayList<>();
        for (Product product : products){
            if (product.getCategory().equalsIgnoreCase(cat)){
                getCat.add(product);
            }
        }
        if (getCat.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        }
        return getCat;
    }

    public Product getOne(int id){
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    public Boolean nameExists(String name, int id){
        for (Product product : products){
            if(product.getName().equals(name) && id != product.getId()){
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product){
        if (nameExists(product.getName(), product.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        products.add(product);
    }

    public Product remove(int id){
        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getId() == id){
                return products.remove(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }

    public Product edit(int id, Product newProduct){
        Product product = this.getOne(id);
        if (nameExists(newProduct.getName(), id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        product.setName(newProduct.getName());
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        return product;
    }
}
