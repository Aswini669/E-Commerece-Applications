package com.cfs.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cfs.security.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String>{

	Optional<AppUser> findByUsername(String name);
	
}
