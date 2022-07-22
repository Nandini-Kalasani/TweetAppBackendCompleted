package com.tweetapp.controller;

import java.util.List;
import java.util.Random;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.ResetPwd;
import com.tweetapp.model.User;
import com.tweetapp.payload.request.LoginRequest;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.UserService;

import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1.0/tweets")
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepo;
	
	
	@PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginRequest lr,@RequestHeader("Authorization") String token) {
		//System.out.println(lr.getLoginId());
		return new ResponseEntity<>(userService.login(lr,token),HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		
		System.out.println("calling register in controller");
		 return new ResponseEntity<>(userService.register(user),HttpStatus.OK);
		
		//return "registered";
		
		
	}
	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody ResetPwd resetpwd) {
		System.out.println("forgot method"+resetpwd.getLoginId());
		String token=RandomString.make(30);
		
		User user=userService.setPassowrdToken(resetpwd.getLoginId(),token);
		if(user!=null&&resetpwd.getNewPwd()!=null) {
			System.out.println("restting");
		   userService.updatePassword(user,resetpwd.getNewPwd());
		   return new ResponseEntity<>("pwd reset done",HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("reset password failed",HttpStatus.OK);
	}
	
	@GetMapping("users/all")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
		
	}
	@GetMapping("/user/search/{username}")
	public ResponseEntity<?> searchUser(@PathVariable String username) {
		
	    //return userRepo.findByFirstName(username);
	    System.out.println(username);
	    User user=userRepo.findByLoginId(username);
	    if(user!=null)
	     return new ResponseEntity<>(user,HttpStatus.OK);
	    else
	    	return new ResponseEntity<>("User not found",HttpStatus.OK);
	    //return userRepo.findByFirstName(username);
			
	}

}
