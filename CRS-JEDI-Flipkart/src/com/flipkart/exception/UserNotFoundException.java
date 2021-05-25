/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class UserNotFoundException extends Exception {
	private int userId;
	
	public UserNotFoundException(int userId) {
		this.userId = userId;
	}
	
	public int getUserId(){
		return userId;
	}
	
	@Override
	public String getMessage() 
	{
		return "User with userId " + userId + " does not exist";
	}

}
