package com.tweetapp.service;

import java.util.List;
import com.tweetapp.model.User;
import com.tweetapp.payload.request.LoginRequest;

public interface UserService{

	public String register(User user);
	public String login(LoginRequest lr,String token);
	public List<User> getAllUsers();
	//public Boolean existsByUsername(String username);
	public User setPassowrdToken(String email, String token);
	public void updatePassword(User user, String newPwd);
	
}
