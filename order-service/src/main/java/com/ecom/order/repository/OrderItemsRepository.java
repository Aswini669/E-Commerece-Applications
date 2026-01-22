package com.ecom.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.order.entity.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

	OrderItems findByOrderId(long orderId);

}
