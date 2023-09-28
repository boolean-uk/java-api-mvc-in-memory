package com.booleanuk.api.bagels;

import java.util.ArrayList;

public class ProductsRepository {
    private ArrayList<Product> products;


    public ProductsRepository() {
        this.products = new ArrayList<>();

//        public Product(String name, String category, int price)
        products.add(new Product("How to build APIS", "Book", 1500));
        products.add(new Product("Head First Java", "Book", 35));
    }

    public ArrayList<Product> getAllProducts() {
        return this.products;
    }

    public Product getOne(int id) {
        for (Product pro : products) {
            if (pro.getId() == id) {
                return pro;
            }
        }
        return null;
    }


    public Product update(int id, String name, String category, int price) {
        for (Product pro : products) {
            if (pro.getId() == id) {
                pro.setName(name);
                pro.setCategory(category);
                pro.setPrice(price);
                return pro;
            }
        }
        return null;
    }

    public Product create(String name, String category, int price) {
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
            return null;
        }
    }

}
