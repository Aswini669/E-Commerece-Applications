package com.ecom.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.client.InventoryClient;
import com.ecom.order.client.PaymentClient;
import com.ecom.order.client.ProductClient;
import com.ecom.order.entity.OrderItems;
import com.ecom.order.entity.Orders;
import com.ecom.order.exception.OrderNotFoundException;
import com.ecom.order.exception.PaymentFailedException;
import com.ecom.order.exception.ProductNotFoundException;
import com.ecom.order.exception.StockFailedException;
import com.ecom.order.kafka.KafkaOrderProducer;
import com.ecom.order.repository.OrderItemsRepository;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.request.OrderRequest;
import com.ecom.order.request.PaymentRequest;
import com.ecom.order.request.StockUpdateRequest;
import com.ecom.order.response.OrderResponse;
import com.ecom.order.response.PaymentResponse;
import com.ecom.order.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImplementation implements OrderService{


	@Autowired
	ProductClient productClient;
	@Autowired
	PaymentClient paymentClient;
	@Autowired
	InventoryClient inventoryClient;
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemsRepository orderItemsRepository;
	@Autowired
	KafkaOrderProducer kafkaOrderProducer;
	
	@Override
	@Transactional
	public long createOrder(OrderRequest request) {
		
		ProductResponse response = productClient.fetchProducts(request.getProductId());
		System.out.println("rdfghgjklhgf" + response.getPrice());
		System.out.println("produc name: " + response.getProductName());
	
		if(response.getProductName() == null) {
			throw new ProductNotFoundException("No prodct found. product id is: " + request.getProductId());
		}
		
		
		Orders orders = new Orders();
		orders.setUserId(request.getUserId());
		orders.setTotalQuantity(request.getQuantity());
		int totalAmount = request.getQuantity() * response.getPrice();
		orders.setTotalAmount(totalAmount);
		orders = orderRepository.save(orders);
		
		OrderItems orderItems = new OrderItems();
		orderItems.setOrderId(orders.getOrderId());
		orderItems.setProductId(response.getProductId());
		orderItems.setProductName(response.getProductName());
		orderItems.setPrice(response.getPrice());
		orderItems.setQuantity(request.getQuantity());
		orderItems.setItemTotal(totalAmount);
		orderItems = orderItemsRepository.save(orderItems);
		
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(totalAmount);
		paymentRequest.setOrderId(orders.getOrderId());
		paymentRequest.setPaymentType("upi");
		PaymentResponse paymentResponse = paymentClient.makePayment(paymentRequest);
		String paymentStatus = paymentResponse.getStatus();
		if(!paymentStatus.equals("SUCCESS")) {
			throw new PaymentFailedException("payment is failed.. Payment status: " + paymentStatus);
		}
		
		orders.setOrderStatus(paymentStatus);
		orders = orderRepository.save(orders);
		
		StockUpdateRequest stockUpdateRequest = new StockUpdateRequest();
		stockUpdateRequest.setProductId(response.getProductId());
		stockUpdateRequest.setReduceStockQty(request.getQuantity());
		
		String message = inventoryClient.updateStock(stockUpdateRequest);
		if(message == null) {
			throw new StockFailedException("Unable to update the stock");
		}
		
		kafkaOrderProducer.sendMessage("Order created successfully.. Yoru order id is: " + orders.getOrderId());
		return orders.getOrderId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		Orders orders = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("No record found for order id: " + orderId));
		
		OrderItems orderItems = orderItemsRepository.findByOrderId(orders.getOrderId());
		
		OrderResponse response = new OrderResponse();
		response.setProductId(orderItems.getProductId());
		response.setProductName(orderItems.getProductName());
		response.setProductPrice(orderItems.getPrice());
		response.setTotalQuantity(orders.getTotalQuantity());
		response.setTotalAmount(orders.getTotalAmount());
		return response;
	}

}
