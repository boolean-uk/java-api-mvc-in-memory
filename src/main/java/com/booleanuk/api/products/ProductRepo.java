package com.booleanuk.api.products;

import com.booleanuk.api.exceptions.ProductNotFoundException;
import com.booleanuk.api.exceptions.ProductsNameAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductRepo {

    private final HashMap<Integer, Product> products;
    private int id;

    public ProductRepo() {
        this.products = new HashMap<>();
        id = 1;

        this.products.put(id++, new Product("How to build APIs", "Book", 1500));
        this.products.put(id++, new Product("Macbook", "Laptop", 3000));
    }

    public ArrayList<HashMap<String, Object>> getAll() {
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Not found");
        }
        ArrayList<HashMap<String, Object>> response = new ArrayList<>();

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", entry.getKey());
            data.put("name", entry.getValue().getName());
            data.put("category", entry.getValue().getCategory());
            data.put("price", entry.getValue().getPrice());

            response.add(data);
        }
        return response;
    }

    public HashMap<String, Object> getOne(int id) {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException("Not found");
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", product.getName());
        response.put("category", product.getCategory());
        response.put("price", product.getPrice());

        return response;
    }

    public HashMap<String, Object> create(Product product) {
        if (productNameExists(product.getName())) {
            throw new ProductsNameAlreadyExistsException("Product name already exists");
        }
        products.put(id++, product);

        HashMap<String, Object> response = new HashMap<>();
        response.put("id", id - 1);
        response.put("name", product.getName());
        response.put("category", product.getCategory());
        response.put("price", product.getPrice());

        return response;
    }

    public HashMap<String, Object> update (int id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("Not found");
        }

        if (productNameExists(updatedProduct.getName())) {
            throw new ProductsNameAlreadyExistsException("Product name already exists");
        }

        products.put(id, updatedProduct);

        HashMap<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", updatedProduct.getName());
        response.put("category", updatedProduct.getCategory());
        response.put("price", updatedProduct.getPrice());

        return response;
    }

    public HashMap<String, Object> delete (int id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("Not found");
        }
        Product deletedProduct = products.remove(id);

        HashMap<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("name", deletedProduct.getName());
        response.put("category", deletedProduct.getCategory());
        response.put("price", deletedProduct.getPrice());

        return response;
    }

    private boolean productNameExists(String name) {
        return products.values().stream().anyMatch(product -> product.getName().equals(name));
    }

}