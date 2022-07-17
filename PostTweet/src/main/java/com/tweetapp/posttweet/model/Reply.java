package com.tweetapp.posttweet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	
	private String replyId;
	
	private String tweetId;
	
	private String userLoginId;
	
    private String comment;
	
    private String repliedOn;
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRepliedOn() {
		return repliedOn;
	}
	public void setRepliedOn(String repliedOn) {
		this.repliedOn = repliedOn;
	}
	public Reply(String replyId, String tweetId, String userLoginId, String comment, String repliedOn) {
		super();
		this.replyId = replyId;
		this.tweetId = tweetId;
		this.userLoginId = userLoginId;
		this.comment = comment;
		this.repliedOn = repliedOn;
	}
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", tweetId=" + tweetId + ", userLoginId=" + userLoginId + ", comment="
				+ comment + ", repliedOn=" + repliedOn + "]";
	}
	
	
	
	
    
	
}
