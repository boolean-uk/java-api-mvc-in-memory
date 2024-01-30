package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Repository
public class ProductRepository {
    private ArrayList<Product> products;

    public ProductRepository(){
        this.products = new ArrayList<>();
//        this.products.add( new Product("apple", "fruits", 35));
//        this.products.add( new Product("orange", "fruits", 35));
//        this.products.add( new Product("sallad", "vegetables", 30));

    }

    public ArrayList<Product> getAll() {
        return this.products;
    }

    public ArrayList<Product> getAll(String category){
        if (category == null){
            return this.products;
        }
        ArrayList<Product> productsInCategory = new ArrayList<>();
        for(Product product: products) {
            if(product.getCategory().equalsIgnoreCase(category)) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }

    public Product create(Product newProduct){

       boolean created = this.products.add(newProduct);
       if (created){
           return  newProduct;
       }
       return null;
    }

    public Product getOne(int id){
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Product update(int id, Product updatedProduct){
//        for(Product p : products){
//            if (p.getName().equalsIgnoreCase(updatedProduct.getName())){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exits");
//            }
//        }
            Product productToUpdate = this.products.stream()
               .filter(product -> product.getId() == id )
               .findFirst()
               .orElse(null);

       if(productToUpdate != null){
           productToUpdate.setName(updatedProduct.getName());
           productToUpdate.setCategory(updatedProduct.getCategory());
           productToUpdate.setPrice(updatedProduct.getPrice());
       }
       //productToUpdate = this.getOne(id);
       return productToUpdate;
    }
    public Product deleteProduct(int id){
        Product productToDelete = this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
        if (productToDelete != null){
            this.products.remove(productToDelete);
        }
        return productToDelete;
    }

//    public Product ProductName(Product product){
//        for (Product p : products){
//            if ()
//        }
//    }


}
