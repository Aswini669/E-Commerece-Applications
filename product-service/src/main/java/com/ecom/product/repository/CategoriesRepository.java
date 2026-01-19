package com.ecom.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.product.entity.CategoriesEntity;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Long>{

	CategoriesEntity findByProductId(long productId);
}
