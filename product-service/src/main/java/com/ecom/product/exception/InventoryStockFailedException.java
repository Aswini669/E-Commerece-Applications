package com.ecom.product.exception;

public class InventoryStockFailedException extends RuntimeException{

	public InventoryStockFailedException(String message){
		super(message);
	}
}
