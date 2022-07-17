package com.tweetapp.posttweet;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.posttweet.model.Reply;
import com.tweetapp.posttweet.model.Tweet;

@SpringBootTest
class PostTweetApplicationTests {

	/*@Test
	void contextLoads() {
		PostTweetApplication.main(new String[] {});
	}*/
	Tweet t=new Tweet();
	@Test
	public void getTweetIdTest() {
		t.setTweetId("103");
		assertEquals("103",t.getTweetId());
	}
	@Test
	public void setTweetId() {
		t.setTweetId("103");
		assertEquals("103",t.getTweetId());
		
	}
	@Test
	public void getUserLoginId() {
		t.setUserLoginId("nandini@gmail.com");
		assertEquals("nandini@gmail.com",t.getUserLoginId());
	}
	@Test
	public void setUserLoginId() {
		t.setUserLoginId("harisha@gmail.com");
		assertEquals("harisha@gmail.com",t.getUserLoginId());
	}
	
	Reply r=new Reply();
	
	@Test
	public void getReplyIdTest() {
		r.setReplyId("replyid1");
		assertEquals("replyid1",r.getReplyId());
	}
	@Test
	public void setReplyIdTest() {
		r.setReplyId("replyid1");
		assertEquals("replyid1",r.getReplyId());
	}
	@Test
	public void getTweetIdTest2() {
		r.setTweetId("101");
		assertEquals("101",r.getTweetId());
	}
	@Test
	public void setTweetIdTest() {
		r.setTweetId("101");
		assertEquals("101",r.getTweetId());
	}
	@Test
	public void getUserLoginIdTest() {
		r.setUserLoginId("nandinikala");
		assertEquals("nandinikala",r.getUserLoginId());
	}
	@Test
	public void setUserLoginIdTest() {
		r.setUserLoginId("nandini");
		assertEquals("nandini",r.getUserLoginId());
	}
	@Test
	public void getCommentTest() {
		r.setComment("comments1");
		assertEquals("comments1",r.getComment());
	}
	@Test
	public void setCommentTest() {
		r.setComment("comments1");
		assertEquals("comments1",r.getComment());
	}
	@Test
	public void getRepliedOnTest() {
		r.setRepliedOn("01-01-2022");
		assertEquals("01-01-2022",r.getRepliedOn());
	}
	@Test
	public void setRepliedOnTest() {
		r.setRepliedOn("01-01-2022");
		assertEquals("01-01-2022",r.getRepliedOn());
	}
	
}
