package com.ecom.cart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

	@PostMapping("cart")
	public String createCart() {
		return null;
	}
}
