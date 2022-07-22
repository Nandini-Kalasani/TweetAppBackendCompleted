package com.tweetapp.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Document("User")
public class User {//implements UserDetails{

	/*@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", loginId=" + loginId
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", contactNumber=" + contactNumber
				+ ", resetPwdToken=" + resetPwdToken + "]";
	}*/
	@NonNull
	public String firstName;
	
	@NonNull
	public String lastName;
	@NonNull
	public String email;
	@NonNull
	public String loginId;
	@NonNull
	public String password;
	@NonNull
	public String confirmPassword;
	@NonNull
	public String contactNumber;
	
	public String resetPwdToken;
	
	public String getResetPwdToken() {
		return resetPwdToken;
	}
	public void setResetPwdToken(String resetPwdToken) {
		this.resetPwdToken = resetPwdToken;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
}
