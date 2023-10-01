package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();

    public Product create(String name, int price, String category)
    {
        Product product = new Product(this.idCounter++, name, price, category);
        this.data.add(product);
        return product;
    }

    public List<Product> findAll()
    {
        return this.data;
    }

    public Product find(int id)
    {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
    public Product update(String name, int price, String category, int id)
    {
        for( Product product: this.data )
        {
            if (product.getId() == id){
                product.setCategory(category);
                product.setName(name);
                product.setPrice(price);
                return product;
            }
        }
        return null;
    }
    public Product delete(int id)
    {
        for ( Product product: this.data)
        {
            if(product.getId() == id)
            {
                this.data.remove(id);
                return product;
            }
        }
        return null;
    }
}