package com.ecom.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendMessage(String message) {
		System.out.println("Email sended with message: " + message);
	}
}
