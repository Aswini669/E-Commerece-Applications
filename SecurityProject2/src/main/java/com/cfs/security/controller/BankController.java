package com.cfs.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfs.security.service.AccountService;

@RestController
@RequestMapping("/api")
public class BankController {

	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/balance")      //only user can access
	public String getBalance() {
		return accountService.getBalance();
	}
	
	@PostMapping("/close")      //only admin can access
	public String closeAccount() {
		return accountService.closeAccount();
	}
	
	@GetMapping("/about")
	public String about() {
		return "hello, chale paisa de....";
	}
}
