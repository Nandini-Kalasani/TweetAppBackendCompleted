package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.tweetapp.model.User;

public interface UserRepository extends MongoRepository<User,String>{
	
   //public List<User> findByFirstName(String name);
  
    //@Query("{firstName : ?0")                                         // SQL Equivalent : SELECT * FROM BOOK where emailId = ?
    public User findByFirstName(String firstName);
   //boolean existsByFirstName(String name);
    public User findByLoginId(String loginId);
	public boolean existsByLoginId(String loginId);
	public User findByEmail(String email);
	public void deleteByLoginId(String loginId);
   
}
