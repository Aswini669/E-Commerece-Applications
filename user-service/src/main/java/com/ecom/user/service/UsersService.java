package com.ecom.user.service;

import org.springframework.stereotype.Service;

import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UserDetails;

@Service
public interface UsersService {

	long createUser(UsersRequest usersRequest);
	UserDetails getUserDetails(long userId);
}
