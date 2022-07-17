package com.tweetapp.posttweet.fiegnClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-login" , url="http://localhost:9090")
public interface AuthClient {

	@GetMapping("/validate/{username}")
	public boolean getValidity(@RequestHeader("Authorization") String token,@PathVariable String username);
}
