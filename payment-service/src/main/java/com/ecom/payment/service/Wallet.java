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
	KafkaPaymentProducer kafkaPaymentProducer;
	
	@Value("${kafka.topics.paymentSuccess}")
	String paymentSuccessTopic;
	@Value("${kafka.topics.paymentFailed}")
	String paymentFailedTopic;
	
	@Override
	@Transactional
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		PaymentEntity entity = new PaymentEntity();
		entity.setOrderId(paymentRequest.getOrderId());
		entity.setAmount(paymentRequest.getAmount());
		entity.setStatus("SUCCESS");
		entity = paymentRepository.save(entity);
		
		if(entity.getStatus().equals("SUCCESS")) {
			kafkaPaymentProducer.sendMessage(paymentSuccessTopic, "Payment SUCCESS for order id: " + entity.getOrderId());
		}else {
			kafkaPaymentProducer.sendMessage(paymentFailedTopic, "Payment FAILED for order id: " + entity.getOrderId());
		}
		
		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(entity.getPaymentId());
		response.setAmount(entity.getAmount());
		response.setStatus(entity.getStatus());
		
		return response;
	}

}
