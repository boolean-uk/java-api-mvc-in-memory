package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>() {{
            add(new Product("TV", "Electronics", 349));
            add(new Product("Phone", "Electronics", 569));
            add(new Product("Horse", "Animal", 619));
        }};
    }

    public List<Product> getAll() {
        return this.products;
    }

    public List<Product> getAllWithCategory(String category) {
        List<Product> listWithCategory = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
        if(listWithCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with this category");
        }
        return listWithCategory;
    }

    public Product getOne(int id) {
        for(Product product : products) {
            if(product.getId() == id) {
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product was not found");
    }

    public Product create(Product newProduct) {
        for(Product product : products) {
            if(product.getName().equalsIgnoreCase(newProduct.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already a product with this name");
            }
        }
        if(newProduct.getName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name was invalid");
        } else if(newProduct.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category was invalid");
        } else if(newProduct.getPrice() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price was invalid");
        }
        products.add(newProduct);
        return products.get(products.size()-1);
    }

    public Product delete(int id) {
        for(Product product : products) {
            if(product.getId() == id) {
                products.remove(product);
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product was not found");
    }

    public Product update(int id, Product newProduct) {
        Product productToBeUpdated = null;
        for(Product product : products) {
            if(product.getName().equalsIgnoreCase(newProduct.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exist");
            }
            if(product.getId() == id) {
                productToBeUpdated = product;
                product.setName(newProduct.getName());
                product.setCategory(newProduct.getCategory());
                product.setPrice(newProduct.getPrice());
            }
        }
        if(productToBeUpdated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return productToBeUpdated;
    }
}
