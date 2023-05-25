package com.booleanuk.api.products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private Map<Integer, Product> products;

    public ProductRepository(){
        products = new HashMap<>();
    }

    public Product create(Product product){
        Product newProduct = new Product(product);
        products.put(newProduct.getId(), newProduct);

        return newProduct;
    }

    public List<Product> getAll(){
        return products.values().stream().toList();
    }

    public Product getByID(int id){
        return products.get(id);
    }

    public Product update(int id, Product newProduct){
        Product old = products.get(id);

        if(old == null) return null;

        old.setName(newProduct.getName());
        old.setCategory(newProduct.getCategory());
        old.setPrice(newProduct.getPrice());

        products.replace(id, old);

        return products.get(id);
    }

    public Product delete(int id){
        return products.remove(id);
    }

}
