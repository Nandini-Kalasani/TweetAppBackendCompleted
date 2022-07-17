package com.tweetapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.CustUserDetails;

@Service
public class CustUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user=userRepo.findByLoginId(username);
		System.out.println("this is load by user name "+user);
		if(user==null) {
			
			throw new UsernameNotFoundException("user not found");
			
		}
		else {
			System.out.println("user found");
			return new CustUserDetails(user);
		}
	}

}
