package com.ecom.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.cart.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{

	CartItems findByCartIdAndProductId(long cartId,long productId);


	int countByCartId(long cartId);

}
