package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<>();
    }

    public List<Product> getAll(){
        return this.products;
    }

    public Product getOne(int id){
        for (Product product : this.products){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public Product create(Product product){
        this.products.add(product);
        return product;
    }

    public Product edit(int id, Product product){
        Product updateProduct = getOne(id);
        int index = this.products.indexOf(updateProduct);
        this.products.get(index).setName(product.getName());
        this.products.get(index).setCategory(product.getCategory());
        this.products.get(index).setPrice(product.getPrice());
        return this.products.get(index);
    }

    public Product delete(int id){
        Product deleteProduct = getOne(id);
        int index = this.products.indexOf(deleteProduct);
        return this.products.remove(index);
    }
}
