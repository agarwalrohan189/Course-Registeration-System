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
 * User Dao Interface
 */
public interface UserDAOInterface {

    /**
     * Method for logging in
     * @param userID -> userID of user logging in
     * @param password -> Password of user logging in
     * @return -> Role of the user
     * @throws PasswordMismatchException
     */
	public Role login(String userID,String password) throws PasswordMismatchException;

    /**
     * Set password for the user
     * @param userID -> userID of user logging in
     * @param newPassword -> Password of user logging in
     * @return -> whether password is set or not
     * @throws UserNotFoundException
     * @throws PasswordMatchedOldException
     * @throws PasswordIsWeakException
     */
    boolean setPassword(String userID, String newPassword) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException;

    /**
     * Get details of the user
     * @param userId -> userID of user whose details we want
     * @return -> User object containing details of user
     * @throws UserNotFoundException
     * @throws StudentNotFoundException
     * @throws ProfNotFoundException
     */
    public User getDetails(String userId) throws UserNotFoundException, StudentNotFoundException, ProfNotFoundException;
	
}
