package com.booleanuk.api.product.exeption;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message){
		super(message);
	}
}