/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author Shubham
 *
 */
public interface UserDAOInterface {

	public Role login(String userID,String password) throws PasswordMismatchException;

    boolean setPassword(String userID, String newPassword) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException;

    public User getDetails(String userId) throws UserNotFoundException, StudentNotFoundException, ProfNotFoundException;
	
}