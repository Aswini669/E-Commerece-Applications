package com.ecom.payment.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPaymentProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaPaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String topic, String message) {
		kafkaTemplate.send(topic, message);
		System.out.println("Message sent to kafka..");
	}
}
