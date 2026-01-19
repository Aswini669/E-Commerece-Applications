package com.ecom.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.user.request.UsersRequest;
import com.ecom.user.response.UserDetails;
import com.ecom.user.service.UsersService;

@RestController
public class UserController {

	@Autowired
	UsersService usersService;
	
	@PostMapping("/users")
	public String createUser(@RequestBody UsersRequest usersRequest) {
		long userId = usersService.createUser(usersRequest);
		return "your profile created successfully: " + userId;
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable long userId){
		UserDetails usersDetails = usersService.getUserDetails(userId);
		return ResponseEntity.ok(usersDetails);
	}
}
