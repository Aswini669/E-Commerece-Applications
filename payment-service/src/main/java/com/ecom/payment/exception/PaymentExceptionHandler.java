package com.ecom.payment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.payment.response.ErrorResponse;

@RestControllerAdvice
public class PaymentExceptionHandler {

	@ExceptionHandler(InvalidPaymentTypeException.class)
	public ResponseEntity<ErrorResponse> invalidPaymentTypeExceptionHandle(InvalidPaymentTypeException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("PE-4001");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
}
