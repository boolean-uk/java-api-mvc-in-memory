package com.booleanuk.api.products;

import org.apache.logging.log4j.util.ProcessIdUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private Map<Integer, Product> products;

    public ProductRepository(){
        products = new HashMap<>();

        Product firstProduct = new Product("Onion Bagel", "Bagel", 254);
        Product secondProduct = new Product("Black Coffee", "Coffee", 220);

        products.put(firstProduct.getId(), firstProduct);
        products.put(secondProduct.getId(), secondProduct);
    }

    public Product create(Product product){
        Product newProduct = new Product(product);
        for(Product temp: this.products.values().stream().toList()){
            if(temp.getName().equalsIgnoreCase(product.getName())){
                return null;
            }
        }
        if(product.getPrice() == 0){
            return null;
        }
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }

    public List<Product> getAll(){
        return products.values().stream().toList();
    }
    public List<Product> getByCategory(String filter){
        return products.values().stream().filter(category -> category.getCategory().equals(filter)).toList();
    }
    public Product getByID(int id){
        return products.get(id);
    }

    public Product update(int id, Product newProduct){
        Product old = products.get(id);
        if(old == null) return new Product(newProduct.getName(), newProduct.getCategory(), -1); // not found
        for(Product temp: this.products.values().stream().toList()){
            if(temp.getName().equalsIgnoreCase(newProduct.getName()) || newProduct.getPrice()==0){
                return null; // null is the product with same name exists or the price is not set up properly
            }
        }
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
