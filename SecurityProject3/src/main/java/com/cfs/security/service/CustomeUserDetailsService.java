package com.cfs.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cfs.security.entity.AppUser;
import com.cfs.security.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loasUsrname method called...");
		AppUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

		System.out.println("Found username = " + user.getUsername() + " / " + user.getPassword());
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).toList()
				);
	}

}
