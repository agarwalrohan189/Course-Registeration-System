/**
 * 
 */
package com.flipkart.exception;

/**
 * @author rohanagarwal
 *
 */
public class StudentNotRegisteredException extends Exception{
	private String name;

	public StudentNotRegisteredException(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return "Student with name "+name+" is not registered"; 
	}
	
}
