package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {


    private ProductRepository theProduct;

    public ProductController() {
        this.theProduct = new ProductRepository();


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        this.theProduct.create(product.getName(),product.getCategory(), product.getPrice());
        return product;

    }

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) String category) {
        return this.theProduct.findAll(category);
    }

//    @GetMapping("/{category}")
//    public List<Product> getCategory(@RequestParam String cate) {
//        return this.theProduct.findCategory();
//    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        return this.theProduct.find(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable(name = "id") int id, @RequestBody Product product){
        Product product1 = this.theProduct.update(id, product);
        if (product1 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The product doesn't exist!");
        }
        return product1;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return this.theProduct.delete(id);
    }




}
