/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class UserNotApprovedException extends Exception {

	private String userId;
	
	public UserNotApprovedException (String id) {
		setUserId(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMessage () {
		return "User with id " + userId + " cannot be approved";
	}
}
