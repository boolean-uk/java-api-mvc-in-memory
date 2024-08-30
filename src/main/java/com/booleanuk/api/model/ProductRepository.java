package com.booleanuk.api.model;

import org.springframework.stereotype.Component;



import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private int id = 1;
    private final List<Product> data = new ArrayList<>();

    public Boolean create(String name, String category, int price) {
        boolean exists = doesProductExist(this.data, name);
        if (!exists) {
            this.data.add(new Product(
                    this.id++,
                    name,
                    category,
                    price
            ));
            return true;
        }
        return false;
    }

    public List<Product> findAll(String category) {
        List<Product> productList = new ArrayList<>();
        if (category != null) {
            productList = this.data.stream()
                    .filter(element -> element.getCategory().toLowerCase().equals(category))
                    .toList();
        }

        if (productList.isEmpty()) {
            return this.data;
        }

        return productList;
    }

    public Product findById(int id) {
        return this.data.stream().filter(
                    prod -> prod.getId() == id
                )
                .findFirst().orElse(null);
    }

    public void updateProduct(Product updated) {
        this.data.replaceAll(prod -> prod.getId() == updated.getId() ? updated : prod);
    }

    public Boolean deleteProduct(int id) {
        return this.data.removeIf(product -> product.getId() == id);
    }

    private Boolean doesProductExist(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Boolean doesCategoryExist(String category) {
        return this.data.stream().anyMatch(prod -> prod.getCategory().toLowerCase().equals(category));
    }
}
