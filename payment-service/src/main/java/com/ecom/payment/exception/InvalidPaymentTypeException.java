package com.ecom.payment.exception;

public class InvalidPaymentTypeException extends RuntimeException{

	public InvalidPaymentTypeException(String message) {
		super(message);
	}
}
