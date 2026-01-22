package com.ecom.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

	private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

	private final NotificationService notificationService;

	public NotificationConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@KafkaListener(topics = "${kafka.topics.paymentSuccess}", groupId = "${spring.kafka.consumer.group-id}")

	public void consumePaymentSuccess(String message) {
		log.info("Payment Success message received: {}", message);
		notificationService.sendPaymentSuccessEmail(message);
	}

	@KafkaListener(topics = "${kafka.topics.paymentFailed}", groupId = "${spring.kafka.consumer.group-id}")

	public void consumePaymentFailed(String message) {
		log.info("Payment failed message recieved: {}", message);
		notificationService.sendPaymentFailedEmail(message);
	}

	@KafkaListener(topics = "${kafka.topics.orderCreated}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumeOrderCreated(String message) {
		log.info("ORDER CREATED message received: {}", message);
		notificationService.sendOrderCreatedEmail(message);
	}

}
