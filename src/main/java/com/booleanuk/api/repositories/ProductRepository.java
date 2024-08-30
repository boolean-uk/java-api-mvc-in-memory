package com.booleanuk.api.repositories;

import com.booleanuk.api.models.Bagel;
import com.booleanuk.api.models.Book;
import com.booleanuk.api.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Bagel("Onion",  12));
        this.products.add(new Bagel("Plain", 10));
    }

    public Product createProduct(Product product) {
        if(product.getType().equals("Bagel")) {
            return createBagel(product);
        } else if (product.getType().equals("Book")) {
            return createBook(product);
        }else {
            this.products.add(product);
            return product;
        }
    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public Product getById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product deleteById(int id) {
        for (Product product: products) {
            if (product.getId() == id) {
                products.remove(product);
                return product;
            }
        }
        return null;
    }

    public Product updatePublisher(int id, Product publisher) {
        for (int i = 0; i < this.products.size(); i++) {
            if(products.get(i).getId() == id) {
                publisher.setId(products.get(i).getId());
                this.products.set(i, publisher);
                return this.products.get(i);
            }
        }
        return null;
    }

    public ArrayList<Product> getAllTypeOfProducts(String type){
        ArrayList<Product> typeList = new ArrayList<>();

        for (Product product: products) {
            if(product.getType().equalsIgnoreCase(type)){
                typeList.add(product);
            }
        }
        return typeList;
    }

    private Product createBagel(Product product) {
        Product bagel = new Bagel(product.getName(), product.getPrice());
        this.products.add(bagel);
        return bagel;
    }

    private Product createBook(Product product) {
        Product book = new Book(product.getName(), product.getPrice());
        this.products.add(book);
        return book;
    }
}
