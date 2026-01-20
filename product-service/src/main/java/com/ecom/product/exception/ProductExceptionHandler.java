package com.ecom.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.product.response.ErrorResponse;

@RestControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler(InvalidProductIdException.class)
	public ResponseEntity<ErrorResponse> invalidProductIdExceptionHandle(InvalidProductIdException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("IE-8745");
		errorResponse.setErrorMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
		
	}
}
