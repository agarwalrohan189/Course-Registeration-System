/**
 * 
 */
package com.flipkart.bean;

/**
 * @author rohanagarwal
 *
 */
public class Course {
	
	private int courseId;
	private String courseName;
	private String instructorId;
	private String instructorName;
	public static final int MAX_SEATS = 10;
	private int filledSeats;
	
	public Course(int courseId, String courseName, String instructorId, String instructorName, int filledSeats) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.filledSeats = filledSeats;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
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

	public int getFilledSeats() {
		return filledSeats;
	}

	public void setFilledSeats(int seats) {
		this.filledSeats = seats;
	}
	
	
	
	
}
