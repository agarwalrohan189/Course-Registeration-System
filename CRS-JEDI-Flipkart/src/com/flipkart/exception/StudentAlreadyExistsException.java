/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Shubham
 *
 */
public class StudentAlreadyExistsException extends Exception {

	private String studentID;

	public StudentAlreadyExistsException(String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return this.studentID;
	}

	public void setUserId(String userId) {
		this.studentID = userId;
	}

	public String getMessage() {
		return "User with id: "+ this.studentID +" already exists!";
	}
}
