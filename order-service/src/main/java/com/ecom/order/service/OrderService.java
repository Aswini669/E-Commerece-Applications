package com.ecom.order.service;

import org.springframework.stereotype.Service;

import com.ecom.order.request.OrderRequest;
import com.ecom.order.response.OrderResponse;

@Service
public interface OrderService {

	long createOrder(OrderRequest request);
	
	OrderResponse getOrderDetails(long orderId);
}
