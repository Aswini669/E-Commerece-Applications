package com.ecom.cart.exception;

public class NoProductFoundException extends RuntimeException{

	public NoProductFoundException(String message) {
		super(message);
	}
}
