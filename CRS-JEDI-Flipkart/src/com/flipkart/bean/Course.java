/**
 * 
 */
package com.flipkart.bean;

/**
 * @author rohanagarwal
 *
 */
public class Course {
	
	private String courseId;
	private String courseName;
	private String instructorId;
	private String instructorName;
	private int seats = 10;
	
	public Course(String courseId, String courseName, String instructorId, String instructorName, int seats) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.seats = seats;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
	
	
}
