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

	/**
	 * @param courseId
	 */
	public CourseNotFound(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
     * Overrides the error message shown for exception class
     * @return
     */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "The course with ID: "+courseId+" is not found.";
	}
	
	
	
}
