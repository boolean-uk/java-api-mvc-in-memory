package com.booleanuk.api.products;

import java.util.ArrayList;

public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("gum","edibles",1.50d));
        products.add(new Product("Tv","Electronics",200.00d));
    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public Product getOne(int id) {
        for (Product product:products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product addNew(Product newProduct) {
        this.products.add(newProduct);
        return this.products.get(this.products.indexOf(newProduct));
    }

    public Product update(int id, Product product) {
        if (getOne(id) != null){
            getOne(id).setName(product.getName());
            getOne(id).setCategory(product.getCategory());
            getOne(id).setPrice(product.getPrice());
            return getOne(id);
        }
        return null;
    }

    public Product delete(int id) {
        Product product = getOne(id);
        if (product != null) {
            this.products.remove(product);
            return product;
        }
        return null;
    }
}
