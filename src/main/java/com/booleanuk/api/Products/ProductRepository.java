package com.booleanuk.api.Products;





import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data = new ArrayList<>();
    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category , price);
        this.data.add(product);
        return product;
    }
    public List<Product> getAll() {
        return this.data;
    }
    public List<Product> getAllByCategory(String category) {
        List<Product> productsMatching = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getCategory().equals(category)){
                productsMatching.add(data.get(i));
            }
        }
        return productsMatching;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }
    public Optional<Product> find(String name) {
        return this.data.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

    public Product delete(int id) {
        for (int i = 0; i < this.data.size(); i++) {
            if(this.data.get(i).getId() == id){
                Product deletedProduct = this.data.get(i);
                this.data.remove(i);
                return deletedProduct;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
    }
}
