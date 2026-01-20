package com.ecom.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;
import com.ecom.payment.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	@Qualifier("upi")
	PaymentService paymentService;
	
	@PostMapping("/payments")
	public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest paymentRequest) {
	 	PaymentResponse resonse = paymentService.processPayment(paymentRequest);
		return ResponseEntity.ok(resonse);
	}
	
}
