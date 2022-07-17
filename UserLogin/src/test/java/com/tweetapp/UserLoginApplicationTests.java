package com.tweetapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.model.ResetPwd;
import com.tweetapp.model.User;

@SpringBootTest
class UserLoginApplicationTests {

	User user=new User();
	@Test
	public void getResetPwdTokenTest() {
		user.setResetPwdToken("pwdtoken");
		assertEquals("pwdtoken",user.getResetPwdToken());
	}
	@Test
	public void setResetPwdTokenTest() {
		user.setResetPwdToken("pwdtoken");
		assertEquals("pwdtoken",user.getResetPwdToken());
	}
	@Test
	public void getFirstNameTest() {
		user.setFirstName("nandini");
		assertEquals("nandini",user.getFirstName());
	}
	@Test
	public void setFirstNameTest() {
		user.setFirstName("nandini");
		assertEquals("nandini",user.getFirstName());
	}
	@Test
	public void getLastNameTest() {
		user.setLastName("nandini");
		assertEquals("nandini",user.getLastName());
	}
	@Test
	public void setLastNameTest() {
		user.setLastName("nandini");
		assertEquals("nandini",user.getLastName());
	}
	@Test
	public void getEmailTest() {
		user.setEmail("nandini");
		assertEquals("nandini",user.getEmail());
	}
	@Test
	public void setEmailTest() {
		user.setEmail("nandini");
		assertEquals("nandini",user.getEmail());
	}
	@Test
	public void getLoginId() {
		user.setLoginId("nandini");
		assertEquals("nandini",user.getLoginId());
	}
	@Test
	public void setLoginId() {
		user.setLoginId("nandini");
		assertEquals("nandini",user.getLoginId());
	}
	@Test
	public void getPasswordTest() {
		user.setPassword("pwd");
		assertEquals("pwd",user.getPassword());
	}
	@Test
	public void setPasswordTest() {
		user.setPassword("pwd");
		assertEquals("pwd",user.getPassword());
	}
	@Test
	public void getConfirmPasswordTest() {
		user.setConfirmPassword("pwd");
		assertEquals("pwd",user.getConfirmPassword());
	}
	@Test
	public void setConfirmPasswordTest() {
		user.setConfirmPassword("pwd");
		assertEquals("pwd",user.getConfirmPassword());
	}
	@Test
	public void getContactNumberTest() {
		user.setContactNumber("123456789");
		assertEquals("123456789",user.getContactNumber());
	}
	@Test
	public void setContactNumberTest() {
		user.setContactNumber("123456789");
		assertEquals("123456789",user.getContactNumber());
	}
	ResetPwd r=new ResetPwd();
	@Test
	public void getLoginIdTest() {
		r.setLoginId("nandinikalasani");
		assertEquals("nandinikalasani",r.getLoginId());
	}
	@Test
	public void setLoginIdTest() {
		r.setLoginId("nandinikalasani");
		assertEquals("nandinikalasani",r.getLoginId());
	}
	@Test
	public void getNewPwdTest() {
		r.setNewPwd("password");
		assertEquals("password",r.getNewPwd());
	}
	@Test
	public void setNewPwdTest() {
		r.setNewPwd("password");
		assertEquals("password",r.getNewPwd());
	}

}
