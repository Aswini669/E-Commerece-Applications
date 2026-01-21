package com.ecom.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.cart.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{

	Optional<CartItems>  findByCartIdAndProductId(long cartId, long productId);

	int countByCartId(long cartId);
	
	List<CartItems> findByCartId(long cartId);

}
