package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository theProducts;
    public ProductController(){
        this.theProducts = new ProductRepository();
    }

    @GetMapping
    public List<Product> getAllProduct(@RequestParam(name = "category", required = false) String category) {
        if (category == null) {
            return theProducts.getAll();
        } else {
            List<Product> products = theProducts.getAll(category);
            if (products.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found");
            }
            return products;
        }
    }


    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable int id){
        Product product = this.theProducts.getOne(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        for (Product p : theProducts.getAll()){
            if (p.getName().equalsIgnoreCase(product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exits");
            }
        }
        this.theProducts.create(product);
        return product;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){
        Product productToUpdate = this.theProducts.update(id, product);
        if(productToUpdate == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        for(Product p : this.theProducts.getAll()){
            if (p.getName().equalsIgnoreCase(product.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exits");
            }
        }



        return  this.theProducts.update(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id){
        Product product = this.theProducts.getOne(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return this.theProducts.deleteProduct(id);
    }
}
