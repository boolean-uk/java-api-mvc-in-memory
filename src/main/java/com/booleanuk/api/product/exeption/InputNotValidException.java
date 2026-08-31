package com.booleanuk.api.product.exeption;

public class InputNotValidException extends RuntimeException{
	public InputNotValidException(String message){
		super(message);
	}
}
