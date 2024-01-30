package com.booleanuk.api.bagels.requests.repositories;

import com.booleanuk.api.bagels.requests.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product("Monster", "Energy", 15));
        this.products.add(new Product("Booster", "Energy", 30));
        this.products.add(new Product("Banana", "Fruit", 3));
        this.products.add(new Product("Mango", "Fruit", 20));
        this.products.add(new Product("Macbook", "Electronics", 15000));
    }

    public Product getOne(int id) {
        for (Product p : this.products) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id: " + id + " does not exist!");
    }

    public List<Product> getAll() {
        return this.products;
    }

    public List<Product> getByCategory(String category) {
        if (checkIfProductCategoryExists(category)) {
            return products.stream().filter(product -> product.getCategory().equals(category)).toList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, category + " does not exist!");
        }
    }


    public Product deleteOne(int id) {
        for (Product p : this.products) {
            if (p.getId() == id) {
                this.products.remove(p);
                return p;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id: " + id + " does not exist!");
    }

    public Product createOne(Product p) {
        if (checkIfProductNameExists(p)) {
            this.products.add(p);
            return p;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, p.getName() + " already exists!");
        }
    }

    public Product updateOne(int id, Product product) {
        if (!checkIfProductNameExists(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, product.getName() + " already exists!");
        } else {
            for(Product p : this.products) {
                if (p.getId() == id) {
                    p.setName(product.getName());
                    p.setCategory(product.getCategory());
                    p.setPrice((product.getPrice()));
                    return p;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, product.getName() + " not found in database");
    }

    /**
     * Helper method
     * @param product
     * @return
     */
    public boolean checkIfProductNameExists(Product product) {
        for(Product p : this.products) {
            if (p.getName().equals(product.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method
     * @param category
     * @return
     */

    public boolean checkIfProductCategoryExists(String category) {
        for(Product p : this.products) {
            if (p.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }
}
