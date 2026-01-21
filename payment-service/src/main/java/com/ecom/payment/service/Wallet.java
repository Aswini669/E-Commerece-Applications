package com.ecom.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecom.payment.entity.PaymentEntity;
import com.ecom.payment.kafka.KafkaPaymentProducer;
import com.ecom.payment.repository.PaymentRepository;
import com.ecom.payment.request.PaymentRequest;
import com.ecom.payment.response.PaymentResponse;

import jakarta.transaction.Transactional;

@Service("wallet")
public class Wallet implements PaymentService{

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	KafkaPaymentProducer kafaKafkaPaymentProducer;
	
	@Value("${kafka.topics.paymentSuccessTopic}")
	String paymentSuccessTopic;
	@Value("${kafka.topics.paymentFailedTopic}")
	String paymentFailedTopic;
	@Override
	@Transactional
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setOrderId(paymentRequest.getOrderId());
		paymentEntity.setAmount(paymentRequest.getAmount());
		paymentEntity.setStatus("SUCCESS");
		paymentEntity = paymentRepository.save(paymentEntity);
		
		PaymentResponse paymentResponse = new PaymentResponse();
		paymentResponse.setPaymentId(paymentEntity.getPaymentId());
		paymentResponse.setAmount(paymentEntity.getAmount());
		paymentResponse.setStatus(paymentEntity.getStatus());
		
		return paymentResponse;
	}

}
