package com.ecom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.user.entity.RolesEntity;
import com.ecom.user.entity.UsersEntity;
import com.ecom.user.exception.InvalidUserIdException;
import com.ecom.user.repositories.RolesRepository;
import com.ecom.user.repositories.UsersRepository;
import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UserDetails;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	RolesRepository rolesRepository;

	@Override
	public long createUser(UsersRequest usersRequest) {

		UsersEntity usersEntity = new UsersEntity();
		usersEntity.setFirstName(usersRequest.getFirstName());
		usersEntity.setLastName(usersRequest.getLastName());
		usersEntity.setEmail(usersRequest.getEmail());
		usersEntity.setPhone(usersRequest.getPhone());
		usersEntity.setPassword(usersRequest.getPassword());

		usersEntity = usersRepository.save(usersEntity);

		RolesEntity rolesEntity = new RolesEntity();
		rolesEntity.setRoles(usersRequest.getRole());
		rolesEntity.setUserId(usersEntity.getUserId());
		rolesRepository.save(rolesEntity);
		return usersEntity.getUserId();
	}

	@Override
	public UserDetails getUserDetails(long userId) {
		UsersEntity usersEntity = usersRepository.findById(userId).orElseThrow(() ->  new InvalidUserIdException("please enter valid user id"));
				
		RolesEntity rolesEntity = rolesRepository.findByUserId(usersEntity.getUserId());
		
		UserDetails userDetails = new UserDetails();
		userDetails.setFirstName(usersEntity.getFirstName());
		userDetails.setLastName(usersEntity.getLastName());
		userDetails.setEmail(usersEntity.getEmail());
		userDetails.setMobile(usersEntity.getPhone());
		userDetails.setRole(rolesEntity.getRoles());
		return userDetails;
	}

}
