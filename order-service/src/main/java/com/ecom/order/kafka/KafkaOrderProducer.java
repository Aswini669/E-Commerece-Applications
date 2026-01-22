package com.ecom.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {


	private static String TOPIC = "order-created";
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public KafkaOrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String message) {
		kafkaTemplate.send(TOPIC, message);
		System.out.println("This message sent to kafka..");
	}
}
