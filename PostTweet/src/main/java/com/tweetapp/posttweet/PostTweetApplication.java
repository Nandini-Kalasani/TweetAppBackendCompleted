package com.tweetapp.posttweet;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;


@EnableFeignClients
@SpringBootApplication
public class PostTweetApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PostTweetApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PostTweetApplication.class, args);
	}
 
    	    @KafkaListener(topics = "TweetsTopic", groupId = "group_id")
    	    public void consume(String message) {
    	        logger.info(String.format("$$$$ => Consumed message: %s", message));
    	    }
}
