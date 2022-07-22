package com.tweetapp.posttweet.model;

public class ExceptionMessage {
	
	String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ExceptionMessage [msg=" + msg + "]";
	}

	public ExceptionMessage(String msg) {
		super();
		this.msg = msg;
	}
	

}
