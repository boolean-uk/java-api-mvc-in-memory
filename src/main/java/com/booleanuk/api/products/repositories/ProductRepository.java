package com.booleanuk.api.products.repositories;
import com.booleanuk.api.products.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data ;
    public ProductRepository() {
        this.data = new ArrayList<>();
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++,name,  category, price);
        boolean created = this.data.add(product);
        if (created) {
            return product;
        }
        return null;
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
    public Product update(int id, Product updatedProduct) {
        Product productToUpdate = this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setCategory(updatedProduct.getCategory());
            productToUpdate.setPrice(productToUpdate.getPrice());
        }
        productToUpdate = this.find(id);
        return productToUpdate;
    }
    public Product delete(int id) {
        Product productToDelete = this.find(id);
        if (productToDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found");
        }
        return  productToDelete;
    }
}
