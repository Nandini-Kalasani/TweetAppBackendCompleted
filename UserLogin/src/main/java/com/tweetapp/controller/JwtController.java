package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.payload.request.LoginRequest;
import com.tweetapp.payload.response.JwtResponse;
import com.tweetapp.service.CustUserDetails;
import com.tweetapp.service.impl.CustUserDetailsService;
import com.tweetapp.utility.JwtUtil;

@RestController
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
//@CrossOrigin(origins = "*")
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenicationManager;

	@Autowired
	private CustUserDetailsService userdetailsService;
	
	
	
	@Autowired
	private JwtUtil jwtutil;
	
	//public org.springframework.security.core.Authentication auth;
	
	
	@PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value="Gets all the users", response=JwtResponse.class)
	public ResponseEntity<?> generateToken(@RequestBody LoginRequest jwtRequest) throws Exception{
		
		/*System.out.println(jwtRequest);
		try {
			System.out.println("try ethod " +jwtRequest.getLoginId());
			authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getLoginId(), jwtRequest.getPassword()));
			System.out.println("auth");
			
		}catch(Exception e) {
			
			//e.printStackTrace();
			System.out.println("Bad credentials");
			//throw new Exception("Bad crdentials: "+e);
		}
		*/
		//SecurityContextHolder.getContext().setAuthentication(auth);

		//after no exception area
		System.out.println("catch");
		UserDetails userDetails;
		try {
		 userDetails = userdetailsService.loadUserByUsername(jwtRequest.getLoginId());
		}
		catch(Exception e) {
		   userDetails=null;
		   return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
		   //System.out.println(e);
		}
		final String Token=jwtutil.generateToken(userDetails);
		
		System.out.println("JWT" + Token);
		
		JwtResponse js= new JwtResponse(Token);
		System.out.println(js);
		
		return new ResponseEntity<JwtResponse>(js, HttpStatus.OK);	
	}
	
	
	//@RequestMapping(value = "/fire",  method = RequestMethod.GET,  headers="Accept=*/*")
	/*public ResponseEntity<?> get() {
		
		System.out.println("I am here");
		return ResponseEntity.ok("");
	}*/
	
	@GetMapping("/validate/{username}")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token,@PathVariable("username") String cuser){
		System.out.println("called get validity");
		System.out.println("while validating "+cuser);
		JwtResponse res=new JwtResponse();
		if(token==null) {
			
	
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		else
		{
			//UserDetails ud=userdetailsService.loadUserByUsername(lr.getLoginId());
			final String username = jwtutil.extractUsername(token);
	        System.out.println("true "+username);
	        return new ResponseEntity<>(username.equals(cuser) && !jwtutil.isTokenExpired(token),HttpStatus.OK);
		}
		
		
	}
}
