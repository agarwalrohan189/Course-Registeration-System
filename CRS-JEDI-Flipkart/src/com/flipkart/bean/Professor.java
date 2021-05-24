/**
 * 
 */
package com.flipkart.bean;
import java.time.LocalDate;

/**
 * @author Shubham
 *
 */
public class Professor extends User {

	String department;
	Course courseList[];
	String qualification;
	LocalDate dateOfJoining;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Course[] getCourseList() {
		return courseList;
	}
	public void setCourseList(Course[] courseList) {
		this.courseList = courseList;
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
}
