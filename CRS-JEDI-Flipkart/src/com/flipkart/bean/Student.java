/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Shubham
 *
 */
public class Student extends User {
	String branch;
	Course coursesEnrolled[];
	int batchYear;
	
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
