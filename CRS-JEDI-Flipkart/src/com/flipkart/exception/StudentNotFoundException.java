/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class StudentNotFoundException extends Exception {
	private String studentId;

	public StudentNotFoundException(String userId) {
		this.studentId = userId;
	}
	
	public String getUserId(){
		return this.studentId;
	}
	
	@Override
	public String getMessage() 
	{
		return "User with userId " + this.studentId + " does not exist";
	}

}
