package com.ecom.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendPaymentSuccessEmail(String message) {
		System.out.println("Sending payment success emial: " + message);
	}
	
	public void sendPaymentFailedEmail(String message) {
		System.out.println("Sending payment failed emial: " + message);
	}
	
	public void sendOrderCreatedEmail(String message) {
		System.out.println("Sending orderd created emial: " + message);
	}
}
