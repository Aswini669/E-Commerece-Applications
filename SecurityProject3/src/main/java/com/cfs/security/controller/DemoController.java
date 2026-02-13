package com.cfs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.security.request.UserListRequest;
import com.cfs.security.service.UserService;

@RestController
@RequestMapping("/api")
public class DemoController {

	
	@Autowired
	UserService service;
	
	@PostMapping("/addUsers")
	public String addUsers(@RequestBody UserListRequest request) {
		service.saveUsers(request.getUsers());
		return "Users Added Successfully..";
	}
	
	@GetMapping("/public")
	public String publicMethod() {
		System.out.println("public mehotd called.....");
		return "This is public method";
	}
	
	@GetMapping("/admin")
	public String adminMethod() {
		System.out.println("admin method called....");
		return "This is admin method";
	}
	
	@GetMapping("/user")
	public String userMethod() {
		return "This is user method";
	}
}
