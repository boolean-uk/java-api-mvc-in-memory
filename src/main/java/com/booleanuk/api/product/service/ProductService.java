package com.booleanuk.api.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booleanuk.api.product.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

import com.booleanuk.api.product.model.Product;

@Service
public class ProductService{

	private ProductRepository productRepo;

	public ProductService(ProductRepository productRepo){
		this.productRepo = productRepo;
	}

	public List<Product> getAllProduct(){
		return this.productRepo.getAll();
	}

	public ResponseEntity<Product> createProduct(String name, String category, int price){
		if(price < 0)
			return ResponseEntity.unprocessableEntity().body(null);
		else if(name.isBlank() || category.isBlank())
			return ResponseEntity.unprocessableEntity().body(null);
		else if(this.productRepo.isProduct(name))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		Product prod = this.productRepo.createProduct(name, category, price);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}

	public ResponseEntity<Product> getProduct(int id){
		Optional<Product> optionalProd = this.productRepo.getOne(id);
		if(optionalProd.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(optionalProd.get());
	}

	public ResponseEntity<Product> putProduct(int id, String name, String category, int price){
		if(price < 0)
			return ResponseEntity.unprocessableEntity().body(null);
		else if(name.isBlank() || category.isBlank())
			return ResponseEntity.unprocessableEntity().body(null);
		else if(this.productRepo.isProduct(name))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		Optional<Product> optionalProd = this.productRepo.putOne(id, name, category, price);
		if(optionalProd.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(optionalProd.get());
	}

	public ResponseEntity<Product> deleteProduct(int id){
		Optional<Product> optionalProd = this.productRepo.deleteOne(id);
		if(optionalProd.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(optionalProd.get());
	}

}
