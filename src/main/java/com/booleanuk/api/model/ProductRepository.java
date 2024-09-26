package com.booleanuk.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepository {
    private final List<Product> data = new ArrayList<>();

    public void create(Product product) {
        this.data.add(product);
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product erase(final int uuid) throws NoSuchElementException {
        Product _product = find(uuid);
        data.remove(_product);
        return _product;
    }

    public Product find(int id) throws NoSuchElementException {
        return this.data.stream()
                .filter(bagel -> bagel.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public boolean hasProductByName(final String productName) {
        for (Product product : data)
            if (product.name.equals(productName))
                return true;
        return false;
    }

    public List<Product> getProductsByCategory(String category) {
        final ArrayList<Product> _outList = new ArrayList<>();
        category = category.toLowerCase();

        for (Product p : data)
            if (p.category.toLowerCase().equals(category))
                _outList.add(p);

        return _outList;
    }
}
