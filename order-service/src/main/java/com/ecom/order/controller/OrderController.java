package com.ecom.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request){
		logger.info("Create Order request recieved: {}",request);
		long orderId = orderService.createOrder(request);
		return ResponseEntity.ok("Order placed successfully. Order id is: " +  orderId);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
		logger.info("Get order request recieved: {}", orderId);
		OrderResponse response = orderService.getOrderDetails(orderId);
		return ResponseEntity.ok(response);
	}
}
