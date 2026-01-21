package com.ecom.cart.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.cart.response.ErrorResponse;

@RestControllerAdvice
public class CartExceptionHandle {

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ErrorResponse> cartNotFoundExceptionHanlder(CartNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("CE-8970");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
