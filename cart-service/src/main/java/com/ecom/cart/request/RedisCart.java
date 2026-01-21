package com.ecom.cart.request;

import java.util.List;

import com.ecom.cart.entity.Cart;
import com.ecom.cart.entity.CartItems;

public class RedisCart {

	private Cart cart;
	private List<CartItems> items;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<CartItems> getItems() {
		return items;
	}

	public void setItems(List<CartItems> items) {
		this.items = items;
	}

}
