package com.ecom.order.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.order.response.ErrorResponse;

@RestControllerAdvice
public class OrderExceptionHandle {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> productNotFoundExceptionHandle(ProductNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("OE-1001");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<ErrorResponse> paymentFailedExceptionHandle(PaymentFailedException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("OER-1002");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(StockFailedException.class)
	public ResponseEntity<ErrorResponse> stockFailedExceptionHandle(StockFailedException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("OER-1003");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> orderNotFoundExceptionHandle(OrderNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("OER-1004");
		errorResponse.setErrCode(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
}
