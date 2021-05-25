/**
 * 
 */
package com.flipkart.dao;

import com.flipkart.bean.User;
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

	public boolean login(String userID,String password) throws UserNotFoundException, PasswordMismatchException;

    boolean setPassword(String userID, String newPassword) throws PasswordMatchedOldException, PasswordIsWeakException;

    public User getDetails(String userId) throws UserNotFoundException, StudentNotFoundException, ProfNotFoundException;
	
}
