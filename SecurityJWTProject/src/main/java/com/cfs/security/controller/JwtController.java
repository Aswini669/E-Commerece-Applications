package com.cfs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.security.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class JwtController {

	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")
	public String generateToken(@RequestParam String username, @RequestParam String pass) {
		if("admin".equals(username) && "admin".equals(pass)) {
			return jwtUtil.generateToken(username); 
		}
		else {
			return "invalid credentials";
		}
	}
	
	@GetMapping("/fund")
	public String fundTranfer(@RequestHeader(value = "Authorization",required = false) String authorizationHeader) {
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			
			if(jwtUtil.validateToken(token)) {
				return "This is secure API, token valid";
			}
			else {
				return "Invalid token";
			}
		}
		else {
			return "AUthorization header is missing";
		}
	}
}
