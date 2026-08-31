package com.booleanuk.api.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booleanuk.api.product.service.ProductService;

import java.util.List;
import com.booleanuk.api.product.model.Product;
import com.booleanuk.api.product.model.ProductCreateDto;

@RestController
@RequestMapping("/products")
public class ProductController{
	private ProductService productService;

	public ProductController(ProductService productService){
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> prods = this.productService.getAllProduct();
		return ResponseEntity.ok(prods);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id){
		ResponseEntity<Product> res = this.productService.getProduct(id);
		return res;
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto productDto){
		ResponseEntity<Product> res = this.productService.createProduct(productDto.getName(), productDto.getCategory(), productDto.getPrice());
		return res;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> putProduct(@PathVariable int id, @RequestBody ProductCreateDto productDto){
	ResponseEntity<Product> res = this.productService.putProduct(id, productDto.getName(), productDto.getCategory(), productDto.getPrice());
		return res;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		ResponseEntity<Product> res = this.productService.deleteProduct(id);
		return res;
	}

}
