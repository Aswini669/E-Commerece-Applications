package com.ecom.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaNotificationConsumer {

	private static final Logger log =
            LoggerFactory.getLogger(KafkaNotificationConsumer.class);
	
	
	private final NotificationService notificationService;
	
	public KafkaNotificationConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	public void consume(String message) {
		try {
			log.info("Recieved message: {}", message);
			notificationService.sendMessage(message);
			log.info("Message sent successfully");
		} catch (Exception e) {
			log.error("Failed to send message," , e);
		}
	}
}
