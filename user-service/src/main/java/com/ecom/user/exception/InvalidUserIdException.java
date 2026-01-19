package com.ecom.user.exception;

public class InvalidUserIdException extends RuntimeException{

	public InvalidUserIdException(String message) {
		super(message);
	}
}
