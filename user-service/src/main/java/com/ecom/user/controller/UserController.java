package com.ecom.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.request.UsersRequest;
import com.ecom.user.service.UsersService;

@RestController
public class UserController {

	@Autowired
	UsersService usersService;
	
	@PostMapping("createUser")
	public String createUser(@RequestBody UsersRequest usersRequest) {
		long userId = usersService.createUser(usersRequest);
		return "your profile created successfully: " + userId;
	}
	
	
}
