/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class UserNotFoundException extends Exception {
	private String userId;
	
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	@Override
	public String getMessage() 
	{
		return "User with userId " + userId + " does not exist";
	}

}
