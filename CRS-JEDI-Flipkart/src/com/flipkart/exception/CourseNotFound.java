/**
 * 
 */
package com.flipkart.exception;

/**
 * @author rohanagarwal
 *
 */
public class CourseNotFound extends Exception {
	
	private int courseId;

	public CourseNotFound(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public String getMessage() {
		return "The course with ID: "+courseId+" is not found."; 
	}
	
	
	
	
	
}
