package com.booleanuk.api.repository;

import com.booleanuk.api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
	private int idCounter = 1;
	private List<Product> data = new ArrayList<>();

	public Product create(Product product) {
		boolean nameExists = data.stream().anyMatch(existingProduct -> existingProduct.getName().equals(product.getName()));

		if (nameExists) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
		}
		product.setId(idCounter++);
		this.data.add(product);
		return product;
	}

	public List<Product> findAll(String category) {
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
		}
		if (category == null) {
			return this.data;
		}
		List<Product> list = this.data.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
		}
		return list;
	}

	public Product find(int id) {
		for (Product product : data) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
	}

	public Product delete(int id) {
		for (Product product : this.data) {
			if (product.getId() == id) {
				data.remove(product);
				return product;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
	}

	public Product update(int id, Product newProduct) {
		boolean nameExists = data.stream().anyMatch(existingProduct -> existingProduct.getName().equals(newProduct.getName()));

		if (nameExists) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists");
		}
		for (Product product : this.data) {
			if (product.getId() == id) {
				product.setName(newProduct.getName());
				product.setCategory(newProduct.getCategory());
				product.setPrice(newProduct.getPrice());
				return product;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
	}
}
