/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class UserNotAddedException extends Exception {

	private String userId;

	public UserNotAddedException (String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return "User with id: "+userId+" cannot be added";
	}
	
}
