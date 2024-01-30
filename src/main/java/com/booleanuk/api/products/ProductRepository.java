package com.booleanuk.api.products;

import com.booleanuk.api.bagels.Bagel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> data = new ArrayList<>();

    public ProductRepository () {
        data.add(new Product("Car model", "Toy", 15));
    }

    public void create(String name, String category, int price) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product name exist!");
            }
        }
        Product product = new Product(name, category, price);
        this.data.add(product);
    }

    public List<Product> findAll(String category) {
        if (category == null) {
            return data;
        }
        List<Product> categoryList = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCategory().equalsIgnoreCase(category)){
                categoryList.add(data.get(i));
            }
        }
        if (categoryList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The list is empty!");
        }
        return categoryList;
    }

    public Product find(int id) {

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id){
                return data.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The item doesn't exist!");
    }

    public Product update(int id, Product updatedProduct) {
        for(Product product : data){
            if (product.getId() ==  id) {
                product.setId(id);
                if (updatedProduct.getName().equals(product.getName())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already has that name!");

                }
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                return updatedProduct;
            }
        }
        return null;
    }

    public Product delete(int id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == id){
                data.remove(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product doesn't exist!");
    }

}
