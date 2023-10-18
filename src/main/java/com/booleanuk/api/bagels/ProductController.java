package com.booleanuk.api.bagels;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository = new ProductRepository();

    public ProductController(ProductRepository pR) { this.productRepository = pR;};

    //we are going to start with POST REQUEST
    // WE NEEDED TO ADD THE CLASSES
    @GetMapping
    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    //now we are just getiing one product, so we need to say were the Mapping is going to go
    @GetMapping("/{id}")
    public Product getOne(@PathVariable(name = "id") int id){
        Product findProduct =  this.productRepository.find(id);
        if(findProduct == null){ //i dont know haha lol lets write null
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " NOT FOUNT MESSAGE");
        }
        return findProduct; //we need to do somehting similiar to here
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createOne(@RequestBody Product newProduct){ //we need a ReqeusBody as this is what Insomnia is going to expect from the user
        return productRepository.create(newProduct.getName(), newProduct.getPrice(), newProduct.getCategory());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable(name = "id") int id, @RequestBody Product product){
        Product newProduct = productRepository.update(product.getName(), product.getPrice(), product.getCategory(), id);
        if(newProduct == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " NOT FOUNT MESSAGE");
        }
        return newProduct;

    }

    //and then we need a Delete and we are done :)
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable (name = "id") int id){
        Product product = this.productRepository.delete(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " NOT FOUNT MESSAGE");
        }
        return product;
    } //DONE :)







}
