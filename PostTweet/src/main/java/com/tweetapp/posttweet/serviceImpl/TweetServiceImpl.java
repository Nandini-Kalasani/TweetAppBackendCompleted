package com.tweetapp.posttweet.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.posttweet.fiegnClient.AuthClient;
import com.tweetapp.posttweet.model.ExceptionMessage;
import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.model.Tweet;
import com.tweetapp.posttweet.repo.ReplyRepo;
import com.tweetapp.posttweet.repo.TweetRepo;
import com.tweetapp.posttweet.service.ReplyService;
import com.tweetapp.posttweet.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{

	@Autowired
	TweetRepo tweetRepo;
	@Autowired
	ReplyService replyService;
	@Autowired
	public AuthClient authClient;
	public static final String topic="TweetsTopic";
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public Object getAllTweets(String token,String loginId) {
		// TODO Auto-generated method stub
		System.out.println("entered  method getall tweets");
		if(token!=null&&loginId!=null) {
			//System.out.println("session valid");
		List<Tweet> tweets=tweetRepo.findByUserLoginId(loginId);
		kafkaTemplate.send(topic,"getting all tweets of "+loginId);
		
		/*if(tweets.size()==0)
			return new ExceptionMessage("No posts to display");*/
		 return tweets;
	
		}
		else {
			//System.out.println("invalid");
			return new String("session invalid");
		}
	}

	@Override
	public String postTweet(String token,String cuser,Tweet tweet) {
		// TODO Auto-generated method stub
		if(isSessionValid(token,cuser))
		{
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		String date=now.toString();
		tweet.setPostedOn(date);
		
		tweetRepo.save(tweet);
		kafkaTemplate.send(topic,"saving tweet");
		return "Tweet Posted";
		}
		else
			return "session invalid";
	}
	@Override
	public String updateTweet(String token,String cuser,Tweet tweet) {
		if(isSessionValid(token,cuser))
		{
		Tweet t=tweetRepo.findByTweetId(tweet.getTweetId());
		tweetRepo.deleteByTweetId(t.getTweetId());
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		String date=now.toString();
		tweet.setPostedOn(date);
		tweetRepo.save(tweet);
		kafkaTemplate.send(topic,cuser+" Updatating tweet "+tweet.getTweetId());
		return "Tweet Updated";
		}
		else {
			return "session invalid";
		}
	}

	@Override
	public int like(String token,String cuser,String tweetId) {
		if(cuser!=null)
		{
		// TODO Auto-generated method stub
		Tweet t=tweetRepo.findByTweetId(tweetId);
		//System.out.println("Tweet id" +t);
		//List<Tweet> lt=tweetRepo.findByUserLoginId("nandy@gmail.com");
		//System.out.println("tweet :"+lt);
		int likes=t.getLikes();
		t.setLikes(++likes);
		String date=t.getPostedOn();
		tweetRepo.deleteByTweetId(tweetId);
		
		t.setPostedOn(date);
		tweetRepo.save(t);
		kafkaTemplate.send(topic,cuser+" liked tweet "+tweetId);
		return t.getLikes();
		}
		else
		{
			//System.out.println("like else");
			return -1;
		}
		
	}

	@Override
	public String deleteTweet(String token,String loginId,String id) {
		// TODO Auto-generated method stub
		if(isSessionValid(token,loginId))
		{
		Tweet t=tweetRepo.findByTweetId(id);
		tweetRepo.deleteByTweetId(t.getTweetId());
		kafkaTemplate.send(topic,loginId+" liked tweet "+id);
		return "Tweet Deleted";
		}
		else
			return "token invalid";
		
	}
	public boolean isSessionValid(String token,String cuser) {
	   System.out.println("called is isession valid "+token+" "+cuser);
		return authClient.getValidity(token,cuser);
	   
	   
   }

	
}
