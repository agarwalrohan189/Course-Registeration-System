/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;

/**
 * below class describes operation for welcome main menu
 */
public interface UserInterface {
	
	/**
     * @param userID
     * @param password
     * @return role of the user who has logged in
     * @throws UserNotFoundException
     * @throws PasswordMismatchException
     */
    public Role login(String userID,String password) throws UserNotFoundException, PasswordMismatchException;

    /**
     * @param userID
     * @param newPassword
     * @return true if successfully set password
     * @throws UserNotFoundException
     * @throws PasswordMatchedOldException
     * @throws PasswordIsWeakException
     */
    boolean setPassword(String userID, String newPassword) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException;

    /**
     * @param userId
     * @return User object with all user details
     * @throws UserNotFoundException
     * @throws ProfNotFoundException
     * @throws StudentNotFoundException
     */
    public User getDetails(String userId) throws UserNotFoundException, ProfNotFoundException, StudentNotFoundException;
}
