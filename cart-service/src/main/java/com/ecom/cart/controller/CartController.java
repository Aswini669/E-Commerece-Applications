package com.ecom.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;
import com.ecom.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/add")
	public String  addToCart(@RequestBody CartRequest cartRequest) {
		logger.info("Add product to cart request recieved : {}", cartRequest);
		long cartId = cartService.addToCart(cartRequest);
		return "Item successfully added to cart. Cart id is: " + cartId;
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity deleteCartItem(@RequestBody RemoveRequest removeRequest) {
		logger.info("Delete Cart item request recieved: {}",removeRequest);
		cartService.removeCartProduct(removeRequest);
		return ResponseEntity.ok("Item removed successfully");
	}
}
