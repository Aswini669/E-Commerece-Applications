package com.ecom.cart.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.cart.response.ErrorResponse;

@RestControllerAdvice
public class CartExceptionHandle {

	@ExceptionHandler(CartNotFoundException.class)
<<<<<<< HEAD
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
=======
	public ResponseEntity<ErrorResponse> cartNotFoundExceptionHanlder(CartNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("CE-8970");
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
