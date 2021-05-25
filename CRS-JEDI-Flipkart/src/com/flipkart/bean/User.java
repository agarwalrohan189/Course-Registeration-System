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
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDoB() {
		return DoB;
	}
	public void setDoB(Date doB) {
		DoB = doB;
	}
	
	
}
