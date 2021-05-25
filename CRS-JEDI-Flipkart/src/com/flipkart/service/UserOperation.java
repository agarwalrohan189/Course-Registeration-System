/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

/**
 * @author Shubham
 *
 */
public class UserOperation implements UserInterface {

	public boolean login(String userID, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		return false;
	}

	public boolean setPassword(String userID, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDetails(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
