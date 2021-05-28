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

	/**
	 * Constructor
	 * @param courseId
	 * @param courseName
	 * @param instructorId
	 * @param instructorName
	 * @param filledSeats
	 */
	public Course(int courseId, String courseName, String instructorId, String instructorName, int filledSeats) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.filledSeats = filledSeats;
	}
	
	public Course()
	{
		
	}

	/**
	 * getter of courseID
	 * @return courseID
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * setter of courseID
	 * @param courseId -> new course id
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * getter of course name
	 * @return name of course
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * setter of course name
	 * @param courseName -> new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * getter of Instructor ID
	 * @return
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Setter of instructor ID
	 * @param instructorId -> New Instructor ID
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	/**
	 * Getter of instructor name
	 * @return -> Instructor name
	 */
	public String getInstructorName() {
		return instructorName;
	}

	/**
	 * Setter of instructor name
 	 * @param instructorName -> New Instructor name
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * Getter of filled seats
	 * @return -> filled seats
	 */
	public int getFilledSeats() {
		return filledSeats;
	}

	/**
	 * Setter of instructor name
	 * @param seats -> New Instructor name
	 */
	public void setFilledSeats(int seats) {
		this.filledSeats = seats;
	}
	
}
