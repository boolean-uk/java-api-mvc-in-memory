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
    public List<Product> getAllProducts(@RequestParam(name = "category", required = false) String category){
        if(category == null){
            return this.theProducts.getAll();
        }

        List<Product> listByCategory = this.theProducts.getAllProductsByCategory(category);
        if(listByCategory.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no products that have the entered category!");
        }
        return listByCategory;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        Product productToCreate = this.theProducts.isNameAlreadyContainedInList(product);
        if(productToCreate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the same name already exists in the list!");
        }

        return this.theProducts.create(product);
    }


    @GetMapping("{id}")
    public Product getProductById(@PathVariable int id){
        Product productToGet = this.theProducts.getProductById(id);
        if(productToGet == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product with the entered ID!");
        }
        return productToGet;
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProductById(@PathVariable int id, @RequestBody Product product){
        Product aProduct = this.theProducts.getProductById(id);
        if(aProduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product with the entered ID!");
        }

        aProduct = this.theProducts.isNameAlreadyContainedInList(id, product);
        if(aProduct == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with the same name already exists in the list!");
        }

        return this.theProducts.updateProductById(id, product);
    }


    @DeleteMapping("{id}")
    public Product deleteProductById(@PathVariable int id){
        Product productToDelete = this.theProducts.getProductById(id);
        if(productToDelete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product with the entered ID!");
        }

        return this.theProducts.deleteProductById(id);
    }
}
