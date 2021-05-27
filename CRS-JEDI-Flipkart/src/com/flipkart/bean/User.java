/**
 * 
 */
package com.flipkart.bean;


import java.util.Date;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

/**
 * @author rohanagarwal
 *
 */
public abstract class User {
	
	private String Id;
	private String name;
	private Role role;
	private String password;
	private Gender gender;
	private String address;
	private String username;
	private Date DoB;

	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param role
	 * @param password
	 * @param gender
	 * @param address
	 * @param username
	 * @param doB
	 */
	public User(String id, String name, Role role, String password, Gender gender, String address, String username,
			Date doB) {
		super();
		Id = id;
		this.name = name;
		this.role = role;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.username = username;
		DoB = doB;
	}

	/**
	 * @return -> ID of user
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return name of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return role of user
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return get password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return gender of the user
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return Address of user
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return DOB of user
 	 */
	public Date getDoB() {
		return DoB;
	}

	/**
	 * @param doB to set
	 */
	public void setDoB(Date doB) {
		DoB = doB;
	}
}
