package com.tweetapp.posttweet.service;

import java.util.List;

import com.tweetapp.posttweet.model.Reply;

public interface ReplyService {
	
   public String replyTweet(String token,String username,Reply reply,String id);
   public List<Reply> getRepliesByTweet(String token,String cuser,String tweetId);
   //public void deleteReply(String tweetId);
   public String deleteReply(String tokn, String cuser,String replyId);
	
}

