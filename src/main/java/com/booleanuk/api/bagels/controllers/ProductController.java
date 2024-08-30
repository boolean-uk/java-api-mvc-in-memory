package com.booleanuk.api.bagels.controllers;

import com.booleanuk.api.bagels.model.Product;
import com.booleanuk.api.bagels.view.ProductsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
	ProductsRepository prods = new ProductsRepository();

	@GetMapping
	public List<Product> getAll(@RequestParam(required = false) Optional<String> category){
		if (category.isEmpty()){
			return prods.getAll();
		}

		return prods.getAllFromCategory(category.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product createNew(@RequestBody Product product){
		return prods.createNew(product);
	}


	@GetMapping("{id}")
	public Product getById(@PathVariable int id){
		return prods.getById(id);
	}


	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product putToProduct(@PathVariable int id, @RequestBody Product product){
		return prods.putById(id, product);
	}

	@DeleteMapping("{id}")
	public Product deleteById(@PathVariable int id){
		return prods.deleteById(id);
	}
}
