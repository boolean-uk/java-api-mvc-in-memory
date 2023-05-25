package com.booleanuk.api.products;

import java.util.HashMap;
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

}
