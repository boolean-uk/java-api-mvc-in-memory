package com.booleanuk.api.product.repo;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.booleanuk.api.product.model.Product;

@Repository
public class ProductRepository{
	private List<Product> products;

	public ProductRepository(){
		this.products = new ArrayList<Product>();
		this.products.add(new Product("How to build APIs", "Book", 1500));
		this.products.add(new Product("Get good at vim", "Paper", 100));
	}

	public List<Product> getAll(){
		return this.products;
	}

	public Optional<List<Product>> getAll(String category){
		List<Product> tmp = new ArrayList<Product>();
		for (Product p : this.products){
			if(p.getCategory().equals(category))
				tmp.add(p);
		}
		if(tmp.isEmpty())
			return Optional.empty();
		return Optional.of(tmp);
	}

	public Product createProduct(String name, String category, int price){
		Product product = new Product(name, category, price);
		this.products.add(product);
		return product;
	}

	public Optional<Product> getOne(int id){
		for (Product p : this.products){
			if(p.getId() == id)
				return Optional.of(p);
		}
		return Optional.empty();
	}

	public Optional<Product> putOne(int id, String name, String category, int price){
		for (int i = 0; i < Product.getCurrentId()-1; i++){
			Product p = this.products.get(i);
			if(p.getId() == id){
				p.setName(name);
				p.setCategory(category);
				p.setPrice(price);
				return Optional.of(this.products.set(i, p));
			}
		}
		return Optional.empty();
	}

	public Optional<Product> deleteOne(int id){
		for (int i = 0; i < Product.getCurrentId()-1; i++){
			Product p = this.products.get(i);
			if(p.getId() == id){
				Product removed = this.products.remove(i);
				return Optional.of(removed);
			}
		}
		return Optional.empty();
	}

	public boolean isProduct(String name){
		boolean cond = this.products.stream().anyMatch(product -> product.getName().equals(name));
		return cond;
	}

}
