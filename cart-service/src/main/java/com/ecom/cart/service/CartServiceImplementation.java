package com.ecom.cart.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ecom.cart.client.ProductClient;
import com.ecom.cart.entity.Cart;
import com.ecom.cart.entity.CartItems;
import com.ecom.cart.exception.CartNotFoundException;
import com.ecom.cart.exception.NoProductFoundException;
import com.ecom.cart.repository.CartItemsRepository;
import com.ecom.cart.repository.CartRepository;
import com.ecom.cart.request.CartRequest;
import com.ecom.cart.request.RedisCart;
import com.ecom.cart.request.RemoveRequest;
import com.ecom.cart.response.ProductResponse;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	ProductClient productClient;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartItemsRepository cartItemsRepository;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	private static final Duration CART_TTL = Duration.ofMinutes(30);
	
	@Override
	@Transactional
	public long addToCart(CartRequest cartRequest) {
		Cart cart;
		
		if(cartRequest.getCartId() > 0) {
			cart = cartRepository.findById(cartRequest.getCartId())
					.orElseThrow(() -> 
					new CartNotFoundException("No record found for cart id: " + cartRequest.getCartId()));
		}
		else {
			cart = new Cart();
			cart.setUserId(cartRequest.getUserId());
			cart.setTotalQty(0);
			cart.setTotalPrice(0);
			cart = cartRepository.save(cart);
		}
		
		ProductResponse response = productClient.fetchProduct(cartRequest.getProductId());
		
		CartItems cartItem = cartItemsRepository.findByCartIdAndProductId(cart.getCartId(), cartRequest.getProductId()).orElse(null);
		
		if(cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + cartRequest.getQuantity());
		}else {
			cartItem = new CartItems();
			cartItem.setCartId(cart.getCartId());
			cartItem.setProductId(cartRequest.getProductId());
			cartItem.setProductName(response.getProductName());
			cartItem.setPrice(response.getPrice());
			cartItem.setQuantity(cartRequest.getQuantity());
		}
		
		cartItemsRepository.save(cartItem);
		
		int addedQty = cartRequest.getQuantity();
		int addedPrice = addedQty * response.getPrice();
		
		cart.setTotalQty(cart.getTotalQty() + addedQty);
		cart.setTotalPrice(cart.getTotalPrice() + addedPrice);
		
		cartRepository.save(cart);
		
		//Adding cart and cart item to redis
		List<CartItems> allItems = cartItemsRepository.findByCartId(cart.getCartId());
		RedisCart redisCart = new RedisCart();
		redisCart.setCart(cart);
		redisCart.setItems(allItems);
		
		String redisKey = "cart:" + cart.getCartId();
		//store cart in Redis with 30min TTL
		redisTemplate.opsForValue().set(redisKey, redisCart, CART_TTL);
		
		return cart.getCartId();
	}

	@Override
	public void removeCartProduct(RemoveRequest removeRequest) {
		long cartId = removeRequest.getCartId();
		long productId = removeRequest.getProductId();
		
		Cart cart = cartRepository.findById(cartId).orElseThrow(() 
				-> new CartNotFoundException("No record found for cartId: " + cartId));
		
		CartItems cartItems = cartItemsRepository.findByCartIdAndProductId(cartId, productId).orElseThrow(() 
				-> new NoProductFoundException("No product found in cart for product id: " + productId));
		
		cartItemsRepository.delete(cartItems);
		
		if(cartItemsRepository.countByCartId(cartId) == 0) {
			cartRepository.delete(cart);
		}
	}

}
