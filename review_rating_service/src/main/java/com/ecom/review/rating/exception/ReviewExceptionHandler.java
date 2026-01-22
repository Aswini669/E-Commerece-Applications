package com.ecom.review.rating.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.review.rating.response.ErrorResponse;

@RestControllerAdvice
public class ReviewExceptionHandler {

	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<ErrorResponse> noProductFoundExceptionHandle(NoProductFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("REE-3001");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(InvalidProductException.class)
	public ResponseEntity<ErrorResponse> noProductFoundExceptionHandle(InvalidProductException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("REE-3002");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
