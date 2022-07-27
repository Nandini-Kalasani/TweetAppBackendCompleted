package com.tweetapp.posttweet.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.posttweet.fiegnClient.AuthClient;
import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.model.Tweet;
import com.tweetapp.posttweet.repo.ReplyRepo;
import com.tweetapp.posttweet.repo.TweetRepo;
import com.tweetapp.posttweet.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyRepo replyRepo;
	
	@Autowired
	TweetRepo tweetRepo;
	
	@Autowired
	AuthClient authClient;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String topic="TweetsTopic";
    
	@Override
	public String replyTweet(String token,String username,Reply reply,String id) {
		// TODO Auto-generated method stub
		if(token!=null) {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		String date=now.toString();
		reply.setRepliedOn(date);
		reply.setTweetId(id);
		replyRepo.save(reply);
		kafkaTemplate.send(topic,username+" replied to tweet "+id);
		return "Reply added";
	  }
	else
		return "token invalid";
	}

	@Override
	public List<Reply> getRepliesByTweet(String token,String cuser,String tweetId) {
		// TODO Auto-generated method stub
		if(isSessionValid(token,cuser))
		{
		List<Reply> replies=replyRepo.findByTweetId(tweetId);
		kafkaTemplate.send(topic,cuser+" seeing all the replies of tweet "+tweetId);
		return replies;
		}
		else
			return null;
	}

	@Override
	public String deleteReply(String tokn,String cuser,String replyId) {
		// TODO Auto-generated method stub
		//Reply r=replyRepo.findByReplyId(replyId);
		
		if(isSessionValid(tokn,cuser))
		{
		replyRepo.deleteByReplyId(replyId);
		kafkaTemplate.send(topic,cuser+" deleting tweet ");
		return "reply deleted";
		}
		else
			return "token invalid";
	}
	public boolean isSessionValid(String token,String cuser) {
		   
		return authClient.getValidity(token,cuser);
	   
	   
   }

}
