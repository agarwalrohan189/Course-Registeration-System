/**
 * 
 */
package com.flipkart.bean;
import java.util.Date;

/**
 * @author Shubham
 *
 */
public class Professor extends User {

	String department;
	Course courseList[];
	String qualification;
	Date dateOfJoining;
	
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
	
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
}
