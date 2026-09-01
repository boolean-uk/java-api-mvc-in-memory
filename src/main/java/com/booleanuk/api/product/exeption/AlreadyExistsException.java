package com.booleanuk.api.product.exeption;

public class AlreadyExistsException extends RuntimeException{
	public AlreadyExistsException(String message){
		super(message);
	}
}
