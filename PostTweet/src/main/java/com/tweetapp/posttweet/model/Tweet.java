package com.tweetapp.posttweet.model;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {


	private String tweetId;
	
	/*public Tweet(String tweetId, @NonNull String tweet, @NonNull String userLoginId, String postedOn) {
		super();
		//Calendar calendar = Calendar.getInstance();
		//Date now = calendar.getTime();
		this.tweetId = tweetId;
		this.tweet = tweet;
		this.userLoginId = userLoginId;
		this.postedOn = postedOn;
	}*/
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	@NonNull
	private String tweet;
	
	@NonNull
	private String userLoginId;
	//@NonNull
	private String postedOn;
	private int likes;
	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", tweet=" + tweet + ", userLoginId=" + userLoginId + ", postedOn="
				+ postedOn + ", likes=" + likes + "]";
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(String now) {
		this.postedOn = now;
	}
	
	
}
