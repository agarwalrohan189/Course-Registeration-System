/**
 * 
 */
package com.flipkart.bean;
import java.util.Date;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

/**
 * @author Shubham
 *
 */
public class Professor extends User {

	String department;
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
	public Professor(String id, String name, Role role, String password, Gender gender, String address,
			String username, Date doB, String department, String qualification,
			Date dateOfJoining) {
		super(id, name, role, password, gender, address, username, doB);
		this.department = department;
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
