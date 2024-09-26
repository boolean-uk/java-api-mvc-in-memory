package com.booleanuk.api.controller;

import com.booleanuk.api.model.Product;
import com.booleanuk.api.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductRepository productRepository;

	public ProductController() {
		this.productRepository = new ProductRepository();
	}

	@GetMapping
	public List<Product> getAllProducts(@RequestParam(required = false)String category) {

		return productRepository.findAll(category);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {

		return productRepository.create(product);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product updateProduct(@PathVariable int id, @RequestBody Product newProduct) {

		return productRepository.update(id, newProduct);
	}
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id) {

		return productRepository.find(id);
	}

	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable int id) {
		return productRepository.delete(id);
	}
}
