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
	
	@Override
	@Transactional
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setOrderId(paymentRequest.getOrderId());
		paymentEntity.setAmount(paymentRequest.getAmount());
		paymentEntity.setStatus("SUCCESS");
		
		paymentEntity = paymentRepository.save(paymentEntity);
		if(paymentEntity.getStatus().equals("SUCCESS")) {
			kafkaPaymentProducer.sendMessage(paymentSuccessTopic, "Payment SUCCESS for order id: " + paymentEntity.getOrderId());
		}else {
			kafkaPaymentProducer.sendMessage(paymentFailedTopic, "Payment FAILED fro order id: " + paymentEntity.getOrderId());
		}
		
		PaymentResponse response = new PaymentResponse();
		response.setPaymentId(paymentEntity.getPaymentId());
		response.setAmount(paymentEntity.getAmount());
		response.setStatus(paymentEntity.getStatus());
		return response;
	}

}
