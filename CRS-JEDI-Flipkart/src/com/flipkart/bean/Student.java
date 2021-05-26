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
/**
 * @author rohanagarwal
 *
 */
public class Student extends User {
	String branch;
	int batchYear;
	public static final int MAX_COURSES = 4;
	boolean paymentDone;
	boolean isRegistered;

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
	public Student(String id, String name, Role role, String password, Gender gender, String address, String username,
			Date doB, String branch, int batchYear, boolean paymentDone, boolean isRegistered) {
		super(id, name, role, password, gender, address, username, doB);
		this.branch = branch;
		this.batchYear = batchYear;
		this.paymentDone = paymentDone;
		this.isRegistered = isRegistered;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public int getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(int batchYear) {
		this.batchYear = batchYear;
	}
	
	public boolean isPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}

	public boolean getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
}
