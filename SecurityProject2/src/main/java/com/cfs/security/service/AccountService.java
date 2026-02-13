package com.cfs.security.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@PreAuthorize("hasRole('USER')")
	public String getBalance() {
		return "your balance is = 1200000";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public String closeAccount() {
		return "Account closed";
	}
}
