package com.booleanuk.api.product.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booleanuk.api.product.repo.ProductRepository;
import com.booleanuk.api.product.exeption.ResourceNotFoundException;
import com.booleanuk.api.product.exeption.InputNotValidException;
import com.booleanuk.api.product.exeption.AlreadyExistsException;


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
			throw new InputNotValidException("Price cannot be negative");
		else if(name.isBlank() || category.isBlank())
			throw new InputNotValidException("Cannot be empty or blank");
		else if(this.productRepo.isProduct(name))
			throw new AlreadyExistsException("Product with name allready exists");
			
		Product prod = this.productRepo.createProduct(name, category, price);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}

	public ResponseEntity<Product> getProduct(int id){
		Optional<Product> optionalProd = this.productRepo.getOne(id);
		if(optionalProd.isEmpty())
			throw new ResourceNotFoundException("Product not found");

		return ResponseEntity.ok(optionalProd.get());
	}

	public ResponseEntity<Product> putProduct(int id, String name, String category, int price){
		if(price < 0)
			throw new InputNotValidException("Price cannot be negative");
		else if(name.isBlank() || category.isBlank())
			throw new InputNotValidException("Cannot be empty or blank");
		else if(this.productRepo.isProduct(name))
			throw new AlreadyExistsException("Product with name allready exists");
		
		Optional<Product> optionalProd = this.productRepo.putOne(id, name, category, price);
		if(optionalProd.isEmpty())
			throw new ResourceNotFoundException("Product not found");

		return ResponseEntity.ok(optionalProd.get());
	}

	public ResponseEntity<Product> deleteProduct(int id){
		Optional<Product> optionalProd = this.productRepo.deleteOne(id);
		if(optionalProd.isEmpty())
			throw new ResourceNotFoundException("Product not found");

		return ResponseEntity.ok(optionalProd.get());
	}

}
