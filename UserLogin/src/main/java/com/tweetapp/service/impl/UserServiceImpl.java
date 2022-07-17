package com.tweetapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.tweetapp.AuthClient.AuthClient;
import com.tweetapp.controller.JwtController;
import com.tweetapp.model.User;
import com.tweetapp.payload.request.LoginRequest;
import com.tweetapp.payload.response.JwtResponse;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.CustUserDetails;
import com.tweetapp.service.UserService;
import com.tweetapp.utility.JwtUtil;
import org.springframework.kafka.core.KafkaTemplate;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	//@Autowired
	//private AuthClient authClient;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Override
	public String register(User user){
		// TODO Auto-generated method stub
		
	if(user.getFirstName().isEmpty()||user.getLastName().isEmpty()||user.getLoginId().isEmpty()||user.getPassword().isEmpty()||user.getConfirmPassword().isEmpty()||user.getContactNumber().isEmpty())
	{

		try {
		throw new Exception("Enter all the mandatory fields");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	else {
	    if(userRepo.existsByLoginId(user.getLoginId())) {
	    	return "user already exists with the login id";
	    }
	    else if(!user.getLoginId().equals(user.getEmail())) {
	    	return "Login Id and Email should be same";
	    }
	    else
	    { 	
		if(user.getPassword().equals(user.getConfirmPassword())) {
			//PasswordEncoder enc=new BCryptPasswordEncoder();
			
			//String pwd=enc.encode(user.getPassword());
			//user.setPassword(pwd);
			//user.setConfirmPassword(pwd);
			userRepo.save(user);
			}
			else {
				try {
				throw new Exception("Confirm password should be same as password");
				}
				catch(Exception e) {
					return "registration failed. Confirm password should be same as password";
				}
			}
		//userRepo.save(user);
		return "registration success";
		//pwd=enc.encode(pwd);
	}
	}
	return "All the fields are mandatory";
	}

	@Override
	public String login(LoginRequest lr,String token) {
		//System.out.println(lr.getLoginId());
		//if(isSessionValid(token,lr))
		//{
		User user=userRepo.findByLoginId(lr.getLoginId());
		System.out.println(user);
		PasswordEncoder enc=new BCryptPasswordEncoder();
		//String pwd=enc.encode(lr.getPassword());
		
		//System.out.println(user.getPassword());
		//System.out.println(pwd);
		//System.out.println(enc.matches(lr.getPassword(),user.getPassword()));
		//UserDetailsService userDetailsService=null;
		//UserDetails cud=userDetailsService.loadUserByUsername(lr.getLoginId());
		
		if(user!=null) {
			if(user.getPassword().equals(lr.getPassword())){
				//if(enc.matches(lr.getPassword(),user.getPassword())) {
				return "Login success";
			}
			else
				return "Login failed";
		}
		return "Login Failed, please Enter correct Login Id and password";
        
		 
		/*}
		else
			return "session invalid";*/
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		kafkaTemplate.send("TweetsTopic","getting all tweets");
		System.out.println("Message sent to topic USERLOGIN");
	  return userRepo.findAll();	
	}

	@Override
	public User setPassowrdToken(String email,String token) {
		// TODO Auto-generated method stub
		User user=userRepo.findByLoginId(email);
		System.out.println(user);
		if(user!=null)
		{
			user.setResetPwdToken(token);
			//userRepo.deleteByLoginId(user.getLoginId());
			//userRepo.save(user);
			
			return user;
		}
		else
		 return null;
		
	}

	@Override
	public void updatePassword(User user, String newPwd) {
		// TODO Auto-generated method stub
	
			//PasswordEncoder enc=new BCryptPasswordEncoder();
			//String pwd=enc.encode(newPwd);
			user.setPassword(newPwd);
			user.setConfirmPassword(newPwd);
			user.setResetPwdToken(null);
			userRepo.deleteByLoginId(user.getLoginId());
			userRepo.save(user);
			kafkaTemplate.send("TweetsTopic","password reset done successfully");
		
		
	}
	
	/*@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		userRepo.exis
	}*/
   /*public boolean isSessionValid(String token,String cuser) {
	    return authClient.getValidity(token,cuser);
	   
	   
   }*/
}
