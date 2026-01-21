package com.ecom.cart.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.cart.response.ErrorResponse;

@RestControllerAdvice
public class CartExceptionHandle {

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ErrorResponse> cartNotFoundExceptionHandle(CartNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("CE-719");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<ErrorResponse> noProductFoundExceptionHandle(NoProductFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("CE-720");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
