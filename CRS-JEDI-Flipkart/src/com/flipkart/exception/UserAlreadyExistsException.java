/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class UserAlreadyExistsException extends Exception {

	private int userId;

	public UserAlreadyExistsException (int id) {
		setUserId(id);
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return "User with id: "+userId+" already exists!";
	}
}
