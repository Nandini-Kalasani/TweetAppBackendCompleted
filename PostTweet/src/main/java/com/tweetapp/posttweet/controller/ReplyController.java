package com.tweetapp.posttweet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.repo.ReplyRepo;
import com.tweetapp.posttweet.service.ReplyService;


@RestController
//@RequestMapping("/api/v1.0/reply")
//@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
//@CrossOrigin(origins = "*")
public class ReplyController {

	@Autowired
	ReplyRepo replyRepo;
	
	@Autowired
	ReplyService replyService;
	
	
	
	@GetMapping("{username}/AllReplies/{tweetId}")
	public ResponseEntity<?> getRepliesByTweetId(@RequestHeader("Authorization") String token,@PathVariable String tweetId,@PathVariable String username){
		List<Reply> replies=replyService.getRepliesByTweet(token,username,tweetId);
		if(replies!=null)
		 return new ResponseEntity<>(replies,HttpStatus.OK);
		else
			return new ResponseEntity<>("replies not found or token invalid error",HttpStatus.OK);
		
	}
	@PostMapping("{username}/reply/{id}")
	public ResponseEntity<?> reply(@RequestHeader("Authorization") String token,@RequestBody Reply reply,@PathVariable String username,@PathVariable String id) {
	    	//System.out.println(username+"   "+id);
	    return new ResponseEntity<>(replyService.replyTweet(token,username,reply,id),HttpStatus.OK);
	}
	@PostMapping("{username}/deleteReply/{replyId}")
	public ResponseEntity<?> deleteReply(@RequestHeader("Authorization") String token,@PathVariable String replyId,@PathVariable String username) {
	    	//System.out.println(username+"   "+id);
	    //return new ResponseEntity<>(replyService.deleteReply(token,replyId),HttpStatus.OK);
		return new ResponseEntity<>(replyService.deleteReply(token, username,replyId),HttpStatus.OK);
	}
}

