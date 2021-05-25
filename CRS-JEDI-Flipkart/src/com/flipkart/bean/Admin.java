package com.flipkart.bean;
import java.util.Date;

public class Admin extends User {
	
	private Date dateOfJoining;
	
	/**
	 * @param id
	 * @param name
	 * @param role
	 * @param password
	 * @param gender
	 * @param address
	 * @param username
	 * @param doB
	 * @param dateOfJoining
	 */
	public Admin(String id, String name, String role, String password, String gender, String address, String username,
			Date doB, Date dateOfJoining) {
		super(id, name, role, password, gender, address, username, doB);
		this.dateOfJoining = dateOfJoining;
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
