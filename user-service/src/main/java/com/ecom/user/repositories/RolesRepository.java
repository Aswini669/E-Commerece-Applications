package com.ecom.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long>{

	RolesEntity findByUserId(long userId);
}
