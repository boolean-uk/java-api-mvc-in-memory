package com.booleanuk.api.products;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepo = new ProductRepository();

    @GetMapping
    public List<Product> getProducts(@RequestParam (required = false) String category){
        if (category != null){
            return this.productRepo.getByCategory(category);
        }
        return this.productRepo.getAll();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable(name = "id") int id){
        return this.productRepo.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createOneProduct(@RequestBody Product product){
        return this.productRepo.createProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateOneProduct(@PathVariable(name = "id") int id, @RequestBody Product product){
        return this.productRepo.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteOneProduct(@PathVariable(name = "id") int id){
        return this.productRepo.deleteProduct(id);
    }


}
