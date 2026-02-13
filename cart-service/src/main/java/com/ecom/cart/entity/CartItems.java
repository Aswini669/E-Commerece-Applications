package com.ecom.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartItemId;
	private long cartId;
	private long productId;
	private String productName;
<<<<<<< HEAD
	private int price;
	private int quantity;

	public long getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}
=======
	private int quantity;
	private int price;
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

<<<<<<< HEAD
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

=======
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

<<<<<<< HEAD
=======
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
}
