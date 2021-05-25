/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class StudentNotAddedException extends Exception {

	private String studentId;

	public StudentNotAddedException(String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return this.studentId;
	}

	public void setUserId(String userId) {
		this.studentId = userId;
	}

	public String getMessage() {
		return "User with id: "+ this.studentId +" cannot be added";
	}
	
}
