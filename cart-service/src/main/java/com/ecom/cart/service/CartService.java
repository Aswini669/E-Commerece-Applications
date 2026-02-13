package com.ecom.cart.service;

import org.springframework.stereotype.Service;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;

@Service
public interface CartService {

<<<<<<< HEAD
	long addToCart(CartRequest cartRequest);
	
	void removeCartProduct(RemoveRequest removeRequest);
=======
	long addCart(CartRequest cartRequest);
	
	void removeCart(RemoveRequest removeRequest);
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
}
