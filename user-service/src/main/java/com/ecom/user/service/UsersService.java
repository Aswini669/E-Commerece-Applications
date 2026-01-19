package com.ecom.user.service;

import org.springframework.stereotype.Service;

import com.ecom.user.request.UsersRequest;

@Service
public interface UsersService {

	long createUser(UsersRequest usersRequest);
}
