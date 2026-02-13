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

@Service("upi")
public class UPI implements PaymentService{

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	KafkaPaymentProducer kafkaPaymentProducer;
	
	@Value("${kafka.topics.paymentSuccess}")
	String paymentSuccessTopic;
	@Value("${kafka.topics.paymentFailed}")
	String paymentFailedTopic;
	
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
		
<<<<<<< HEAD
		if(entity.getStatus().equals("SUCCESS")) {
			kafkaPaymentProducer.sendMessage(paymentSuccessTopic, "Payment SUCCESS for order id: " + entity.getOrderId());
		}else {
			kafkaPaymentProducer.sendMessage(paymentFailedTopic, "Payment FAILED for order id: " + entity.getOrderId());
=======
		paymentEntity = paymentRepository.save(paymentEntity);
		if(paymentEntity.getStatus().equals("SUCCESS")) {
			kafkaPaymentProducer.sendMessage(paymentSuccessTopic, "Payment SUCCESS for order id: " + paymentEntity.getOrderId());
		}else {
			kafkaPaymentProducer.sendMessage(paymentFailedTopic, "Payment FAILED fro order id: " + paymentEntity.getOrderId());
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
		}
		
		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(entity.getPaymentId());
		response.setAmount(entity.getAmount());
		response.setStatus(entity.getStatus());
		return response;
	}

}
