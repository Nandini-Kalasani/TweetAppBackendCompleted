package com.tweetapp.posttweet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.model.Tweet;


public interface TweetService {

	public Object getAllTweets(String token,String loginId);
	public String postTweet(String token,String cuser,Tweet tweet);
	public String updateTweet(String token,String cuser,Tweet tweet);
	public int like(String token,String cuser,String tweetId);
	public String deleteTweet(String token,String loginId,String id);
	//public String reply(String token,Reply reply,String cuser,String tweetId);
}
