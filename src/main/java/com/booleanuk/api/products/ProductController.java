package com.booleanuk.api.products;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(){
        productRepository = new ProductRepository();
    }

    @PostMapping
    public Product create(Product product){
        return productRepository.create(product);
    }


}
