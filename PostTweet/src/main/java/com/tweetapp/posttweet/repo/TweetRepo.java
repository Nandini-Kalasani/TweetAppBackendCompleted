package com.tweetapp.posttweet.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.posttweet.model.Tweet;
public interface TweetRepo extends MongoRepository<Tweet,String>{
   
	public List<Tweet> findByUserLoginId(String loginId);

	public void deleteByTweetId(String tid);

	public Tweet findByTweetId(String tweetId);
}
