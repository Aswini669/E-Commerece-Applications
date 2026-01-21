package com.ecom.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.cart.client.ProductClient;
import com.ecom.cart.controller.CartController;
import com.ecom.cart.entity.Cart;
import com.ecom.cart.entity.CartItems;
import com.ecom.cart.exception.CartNotFoundException;
import com.ecom.cart.repository.CartItemsRepository;
import com.ecom.cart.repository.CartRepository;
import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RemoveRequest;
import com.ecom.cart.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImplementation implements CartService{

	
	@Autowired
	CartItemsRepository cartItemsRepository;
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ProductClient productClient;
	
	@Override
	@Transactional
	public long addCart(CartRequest cartRequest) {
		Cart cart;
		if(cartRequest.getCartId() > 0) {
			cart = cartRepository.findById(cartRequest.getCartId())
					.orElseThrow(() -> new CartNotFoundException("No record for cartId: " + cartRequest.getCartId()));
		}
		else {
			cart = new Cart();
			cart.setUserId(cartRequest.getUserId());
			cart.setTotalQty(0);
			cart.setTotalPrice(0);
			cart = cartRepository.save(cart);
		}
		
		ProductResponse response = productClient.fetchProduct(cartRequest.getProductId());

		CartItems cartItems = cartItemsRepository.findByCartIdAndProductId(cart.getCartId(), cartRequest.getProductId());
		
		if(cartItems != null) {
			cartItems.setQuantity(cartItems.getQuantity() + cartRequest.getQuantity());
		}else {
			cartItems = new CartItems();
			cartItems.setCartId(cart.getCartId());
			cartItems.setProductId(cartRequest.getProductId());
			cartItems.setProductName(response.getProductName());
			cartItems.setPrice(response.getPrice());
			cartItems.setQuantity(cartRequest.getQuantity());
		}
		
		cartItemsRepository.save(cartItems);
		
		int addedQty = cartRequest.getQuantity();
		int addedPrice = addedQty * response.getPrice();
	
		cart.setTotalQty(cart.getTotalQty() + addedQty);
		cart.setTotalPrice(cart.getTotalPrice() + addedPrice);
		
		cartRepository.save(cart);
		
		return cart.getCartId();
	}

	@Override
	@Transactional
	public void removeCart(RemoveRequest removeRequest) {
		long cartId = removeRequest.getCartId();
		long productId = removeRequest.getProductId();
		
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("No record found for cartId: " + cartId));
		
		CartItems cartItems = cartItemsRepository.findByCartIdAndProductId(cartId, productId);
		cartItemsRepository.delete(cartItems);
		
		if(cartItemsRepository.countByCartId(cartId) == 0) {
			cartRepository.delete(cart);
		}
	}

	
}
