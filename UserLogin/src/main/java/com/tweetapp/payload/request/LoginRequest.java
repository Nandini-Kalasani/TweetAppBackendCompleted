package com.tweetapp.payload.request;

import lombok.NonNull;

public class LoginRequest {
	@Override
	public String toString() {
		return "LoginRequest [loginId=" + loginId + ", password=" + password + "]";
	}

	@NonNull
	private String loginId;

	  @NonNull
	  private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String username) {
		this.loginId = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	  


}
