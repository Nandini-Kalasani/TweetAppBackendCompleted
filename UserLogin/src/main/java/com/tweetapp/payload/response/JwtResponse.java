package com.tweetapp.payload.response;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
//	@JsonProperty("token")
	private String token;

	//private boolean valid;
	//private String uid;
	/*public String getUid() {
		//return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
 */
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/*public void setValid(boolean b) {
		// TODO Auto-generated method stub
		this.valid=b;
		
	}

	public boolean getValid() {
		return valid;
	}*/
	
	

}
