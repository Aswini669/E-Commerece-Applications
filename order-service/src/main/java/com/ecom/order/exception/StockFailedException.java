package com.ecom.order.exception;

public class StockFailedException extends RuntimeException{

	public StockFailedException(String message) {
		super(message);
	}
}
