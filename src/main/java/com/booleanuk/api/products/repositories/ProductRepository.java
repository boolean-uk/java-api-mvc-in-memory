package com.booleanuk.api.products.repositories;

import com.booleanuk.api.products.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product create(Product product) {
        boolean created = this.products.add(product);
        if(created) {
            return product;
        }
        return null;
    }

    public List<Product> getAll() {
        return this.products;
    }

    public Product getOne(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Product update(int id, Product updatedProduct) {
        Product productToUpdate = this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setPrice(updatedProduct.getPrice());

        }
        return this.getOne(id);
    }

    public Product delete(int id) {
        if(id > this.products.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product productID = this.products.get(id);
        this.products.remove(products.get(id));
        return productID;
    }
//        Product productToDelete = this.getOne(id);
//        if(productToDelete == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
//        }
//        this.products.remove(id);
//        return productToDelete;

}
