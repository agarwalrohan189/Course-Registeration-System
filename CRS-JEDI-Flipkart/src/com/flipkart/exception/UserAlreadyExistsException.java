/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class UserAlreadyExistsException extends Exception {

	private String userId;

	public UserAlreadyExistsException (String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return "User with id: "+userId+" already exists!";
	}
}
