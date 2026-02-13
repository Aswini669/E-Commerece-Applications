package com.ecom.cart.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

=======
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.cart.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{

<<<<<<< HEAD
	Optional<CartItems>  findByCartIdAndProductId(long cartId, long productId);

	int countByCartId(long cartId);
	
	List<CartItems> findByCartId(long cartId);
=======
	CartItems findByCartIdAndProductId(long cartId,long productId);


	int countByCartId(long cartId);
>>>>>>> 7b34a51bc93aeda4d71178caf8d35acdf12d1e1a

}
