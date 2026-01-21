package com.ecom.cart.service;

import org.springframework.stereotype.Service;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;

@Service
public interface CartService {

	long addToCart(CartRequest cartRequest);
	
	void removeCartProduct(RemoveRequest removeRequest);
}
