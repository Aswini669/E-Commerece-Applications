package com.ecom.payment.controller;

import java.util.Map;
import com.ecom.payment.tracing.TracingFeignConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.exception.InvalidPaymentTypeException;
import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.ErrorResponse;
import com.ecom.payment.response.PaymentResponse;
import com.ecom.payment.service.PaymentService;

@RestController
public class PaymentController {


	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	Map<String, PaymentService> paymentService;

    
	@PostMapping("/payments")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
		String type = paymentRequest.getPaymentType();
	 	logger.info("Payment request recieved: ", paymentRequest);
	 	if(!paymentService.containsKey(type)) {
	 		throw new InvalidPaymentTypeException("Invalid payment type: " + type);
	 	}
	 	
	 	PaymentService service = paymentService.get(type);
	 	PaymentResponse response = service.processPayment(paymentRequest);
	 	
		return ResponseEntity.ok(response);
	}
	
	@ExceptionHandler(InvalidPaymentTypeException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPaymentTypeException(InvalidPaymentTypeException ex){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrCode("PEE-6002");
		errorResponse.setErrMsg(ex.getMessage());
		return ResponseEntity.ok(errorResponse);
	}
	
}
