package com.ecom.inventory.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.inventory.response.ErrorResponse;

@RestControllerAdvice
public class InventoryExceptionHandler {

	@ExceptionHandler(InvalidIdProductIdException.class)
	public ResponseEntity<ErrorResponse> invalidProductIdExceptionHanlde(InvalidIdProductIdException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IN-9845");
		errorResponse.setErrorMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
