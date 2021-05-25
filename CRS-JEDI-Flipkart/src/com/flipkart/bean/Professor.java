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
	
	
	/**
	 * @param id
	 * @param name
	 * @param role
	 * @param password
	 * @param gender
	 * @param address
	 * @param username
	 * @param doB
	 * @param department
	 * @param courseList
	 * @param qualification
	 * @param dateOfJoining
	 */
	public Professor(String id, String name, String role, String password, String gender, String address,
			String username, Date doB, String department, Course[] courseList, String qualification,
			Date dateOfJoining) {
		super(id, name, role, password, gender, address, username, doB);
		this.department = department;
		this.courseList = courseList;
		this.qualification = qualification;
		this.dateOfJoining = dateOfJoining;
	}


	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}


	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}


	/**
	 * @return the courseList
	 */
	public Course[] getCourseList() {
		return courseList;
	}


	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(Course[] courseList) {
		this.courseList = courseList;
	}


	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}


	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}


	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	
	
}
