package com.tweetapp.posttweet.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.posttweet.model.Reply;

public interface ReplyRepo extends MongoRepository<Reply,String> {

	List<Reply> findByTweetId(String tweetId);
	
    //Reply findByTweetId(String tweetId);
    Reply findByReplyId(String replyId);
    public void deleteByReplyId(String replyId);

	//void delete(String replyId, String tweetId);
}
