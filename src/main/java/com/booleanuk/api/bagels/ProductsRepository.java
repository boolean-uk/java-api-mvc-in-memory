package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class ProductsRepository {
    private ArrayList<Product> products;


    public ProductsRepository() {
        this.products = new ArrayList<>();

//        public Product(String name, String category, int price)
        products.add(new Product("How to build APIS", "Book", 1500));
        products.add(new Product("Head First Java", "Book", 35));
        products.add(new Product("test", "test", 12));
    }

    public ArrayList<Product> getAllProducts() {
        return this.products;
    }

    public ArrayList<Product> getCategory(String cat) {
        ArrayList<Product> newList = new ArrayList<>();

        for (Product pro : products) {
            if (pro.getCategory().equals(cat)) {
                newList.add(pro);
            }
        }

        if (newList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }

        return newList;
    }


    public Product getOne(int id) {
        for (Product pro : products) {
            if (pro.getId() == id) {
                return pro;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
    }


    public Product update(int id, String name, String category, int price) {
        for (Product pro : products) {
            if (pro.getId() == id) {
                for (Product pro2 : products) {
                    if (pro2.getName().equals(name)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
                    }
                }
                pro.setName(name);
                pro.setCategory(category);
                pro.setPrice(price);
                return pro;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
    }

    public Product create(String name, String category, int price) {
        for (Product pro : products) {
            if (pro.getName().equals(name)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found.");
            }
        }
        Product product = new Product(name, category, price);
        this.products.add(product);
        return product;
    }

    public Product delete(int id) {
        //Never delete an item directly using a loop, add it to a var instead
        //Then delete the added var later on with an if statement
        Product productToRemove = null;

        for (Product pro : products) {
            if (pro.getId() == id) {
                productToRemove = pro;
                break;
            }
        }

        if (productToRemove != null) {
            products.remove(productToRemove);
            return productToRemove;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.");
        }
    }

}
