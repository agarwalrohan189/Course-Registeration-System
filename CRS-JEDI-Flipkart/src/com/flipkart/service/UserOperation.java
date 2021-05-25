/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author Shubham
 *
 */
public class UserOperation implements UserInterface {

	public boolean login(String userID, String password) throws UserNotFoundException, PasswordMismatchException {
		// TODO Auto-generated method stub
		
		return false;
	}

	public boolean setPassword(String userID, String newPassword) throws PasswordMatchedOldException, PasswordIsWeakException{
		// TODO Auto-generated method stub
		return false;
	}

	public int getDetails(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

}
