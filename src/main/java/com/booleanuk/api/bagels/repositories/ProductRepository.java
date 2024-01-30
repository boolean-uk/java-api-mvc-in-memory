package com.booleanuk.api.bagels.repositories;

import com.booleanuk.api.bagels.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Bread", "Food", 23.55));
        this.products.add(new Product("Lavashak", "Fod", 3444.4));

    }

    public List<Product> getAll() {

        if (this.products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        return this.products;

    }

    public Product getOne(int id) {

       for (Product product : this.products) {
           if (product.getId() == id) {
               return product;
           }
       }

       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");

    }


    public List<Product> getSpecific(String category) {

        List<Product> categoryList = new ArrayList<>();

    if (!products.isEmpty()) {

        for (Product product : this.products) {
            if (product.getCategory().equals(category)) {
                categoryList.add(product);
            }


        }

        if (categoryList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found.");

        }

        return categoryList;
    }

        return null;

    }


    public Product created(Product product) {
        for (Product product1 : products) {
            if (product1.getName().equals(product.getName()) && product1.getCategory().equals(product.getCategory())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
            }
        }
            this.products.add(product);
            return product;
    }

    public Product updateId(int id, Product product) {

        for (Product product1 : products) {
            if (product1.getName().equals(product.getName()) && product1.getCategory().equals(product.getCategory())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
            }
            else if (id > products.size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
            }
        }

            this.products.get(id).setName(product.getName());
            this.products.get(id).setCategory(product.getCategory());
            this.products.get(id).setPrice(product.getPrice());

            return this.products.get(id);

    }


    public Product deleteId(int id) {

//        for (Product product : this.products) {
//            if (product.getId() == id) {
//                return this.products.remove(id);
//            }
//        }
//
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");



        if (id > products.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        return this.products.remove(id);
    }

}
