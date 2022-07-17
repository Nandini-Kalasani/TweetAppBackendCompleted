package com.tweetapp.posttweet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.model.Tweet;
import com.tweetapp.posttweet.service.ReplyService;
import com.tweetapp.posttweet.service.TweetService;

@RestController("/api/v1.0/tweets/")
public class TweetController {
	
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	public TweetService tweetService;
	@GetMapping("/{username}")
	public ResponseEntity<?> getAllTweets(@RequestHeader("Authorization") String token,@PathVariable String username){
		return new ResponseEntity<>(tweetService.getAllTweets(token,username),HttpStatus.OK);
	}
	
	@PostMapping("{username}/add")
	public ResponseEntity<?> postTweet(@RequestHeader("Authorization") String token,@PathVariable String username,@RequestBody Tweet tweet) {
		return new ResponseEntity<>(tweetService.postTweet(token,username,tweet),HttpStatus.OK);
	}
	
	@PostMapping("{username}/update/{id}")
	public ResponseEntity<?> updateTweet(@RequestHeader("Authorization") String token,@RequestBody Tweet tweet,@PathVariable("username") String cuser) {
		return new ResponseEntity<>(tweetService.updateTweet(token,cuser,tweet),HttpStatus.OK);
	}
	
	@PostMapping("{username}/delete/{id}")
	public ResponseEntity<?> deleteTweet(@RequestHeader("Authorization") String token,@PathVariable String username,@PathVariable String id) {
	
		return new ResponseEntity<>(tweetService.deleteTweet(token,username,id),HttpStatus.OK);
	
	}
	@PostMapping("{username}/like/{id}")
	public ResponseEntity<?> like(@RequestHeader("Authorization") String token,@PathVariable String username,@PathVariable String id) {
		//System.out.println("Id "+id);
		int l=tweetService.like(token,username,id);
		//System.out.println("After like method");
    if(l!=-1)
	 return new ResponseEntity<>("likes: "+l,HttpStatus.OK);
    else
    	return new ResponseEntity<>("token invalid",HttpStatus.OK);
	}
}
