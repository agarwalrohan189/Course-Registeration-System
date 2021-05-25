/**
 * 
 */
package com.flipkart.bean;

import java.util.Date;

/**
 * @author Shubham
 *
 */
/**
 * @author rohanagarwal
 *
 */
public class Student extends User {
	String branch;
	Course coursesEnrolled[];
	int batchYear;
	
	
	/**
	 * @param id
	 * @param name
	 * @param role
	 * @param password
	 * @param gender
	 * @param address
	 * @param username
	 * @param doB
	 * @param branch
	 * @param coursesEnrolled
	 * @param batchYear
	 */
	public Student(String id, String name, String role, String password, String gender, String address, String username,
			Date doB, String branch, Course[] coursesEnrolled, int batchYear) {
		super(id, name, role, password, gender, address, username, doB);
		this.branch = branch;
		this.coursesEnrolled = coursesEnrolled;
		this.batchYear = batchYear;
	}



	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public Course[] getCoursesEnrolled() {
		return coursesEnrolled;
	}
	public void setCoursesEnrolled(Course[] coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}
	
	public int getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(int batchYear) {
		this.batchYear = batchYear;
	}
}
