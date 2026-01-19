package com.ecom.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.user.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExcepionHandler {

	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<ErrorResponse> userIdExceptionHandler(InvalidUserIdException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("EM789");
		errorResponse.setErrorMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
