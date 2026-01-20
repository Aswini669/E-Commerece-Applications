package com.ecom.payment.service;

import org.springframework.stereotype.Service;

import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

@Service
public interface PaymentService {

	PaymentResponse processPayment(PaymentRequest paymentRequest);
}
