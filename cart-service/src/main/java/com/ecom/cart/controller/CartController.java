package com.ecom.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	CartService cartService;

	@PostMapping("/add")
	public String addCart(@RequestBody CartRequest cartRequest) {
		long cartId = cartService.addCart(cartRequest);
		return "your cart id: " + cartId;
	}
	
	@DeleteMapping("/remove")
	public void removeCart(@RequestBody RemoveRequest removeRequest) {
		cartService.removeCart(removeRequest);
	}
}
