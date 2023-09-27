package com.booleanuk.api.utils;

import com.booleanuk.api.products.Product;

import java.util.Comparator;

public class ProductNameComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product1.getName().compareTo(product2.getName());
    }
}
