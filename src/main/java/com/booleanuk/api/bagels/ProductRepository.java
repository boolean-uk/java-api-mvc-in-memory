


package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("authors")
public class ProductRepository {
    private List<Product> products = new ArrayList<>();


    public ProductRepository() {
        Product product = new Product("Firstname", 12, "category");
        Product product1 = new Product("Secondname", 123, "sfs");
        Product product2 = new Product("ThirdName", 12, "cddategory");
        Product product3 = new Product("Fourthname", 122, "casfstegory");
        Product product4 = new Product("Fifthname", 122, "dcasfstegory");
        Product product5 = new Product("sixtname", 122, "bcasfstegory");
        Product product6 = new Product("seventhname", 122, "casfstegory");
        Product product7 = new Product("eightname", 122, "acasfstegory");

        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);



    }

    public void delete(int id){


        for (; id < products.size()-1; id++){
            products.set(id, products.get(id+1));
        }

    }

    public Product create(Product product) {
        Product.nextID = products.size();
        product.setId(Product.nextID);
        this.products.add(product);
        return product;
    }

    public List<Product> findAll() {
//        Product product = new Product("Namee", 13, "12",this.idCounter++);
//        this.products.add(product);
        return this.products;
    }

    public List<Product> findAllSorted(){
        List<Product> sortedList = new ArrayList<>();
        List<String> newlist = new ArrayList<>();

        for (Product product : products){
            newlist.add(product.getCategory());

        }
        Collections.sort(newlist);
        for (String cat : newlist){
            sortedList.add(products.get(hasCat(cat)));
        }
        return sortedList;
    }

    public int hasCat(String item){
        for (Product product : products){
            if (Objects.equals(product.getCategory(), item)){
                return product.getId();
            }
        }
        return -1;
    }



    public Product update(int id, Product product){
        if (find(id) != null){
            Product prodToUpdate = find(id);
            product.setId(id);

            products.set(id, product);

            return product;
        }
        return null;




    }

    public Product find(int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;

    }
}
