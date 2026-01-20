package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.payment.entity.PaymentEntity;
import com.ecom.payment.repository.PaymentRepository;
import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

import jakarta.transaction.Transactional;

@Service("card")
public class Card implements PaymentService{

	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	@Transactional
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setOrderId(paymentRequest.getOrderId());
		paymentEntity.setAmount(paymentRequest.getAmount());
		paymentEntity.setStatus("FAILED");
		
		paymentEntity = paymentRepository.save(paymentEntity);
		
		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(paymentEntity.getPaymentId());
		response.setAmount(paymentEntity.getAmount());
		response.setStatus(paymentEntity.getStatus());
		return response;
	}

}
