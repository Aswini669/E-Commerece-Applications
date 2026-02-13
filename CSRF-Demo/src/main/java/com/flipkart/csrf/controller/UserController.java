package com.flipkart.csrf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	private Map<String, String> userData = new HashMap<String, String>();
	
	
	@GetMapping("/public")
	public String publicMethod() {
		return "This is public method";
	}
	
	@PostMapping("/addUser")
	public String getUser(@RequestBody Map<String, String> data) {
		userData.put(data.get("username"), userData.get("password"));
		return "user saved in memory";
	}
}
