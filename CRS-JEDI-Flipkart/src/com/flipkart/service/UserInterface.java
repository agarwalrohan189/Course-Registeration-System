/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {
    public boolean login(String userID,String password) throws UserNotFoundException, PasswordMismatchException;

    boolean setPassword(String userID, String newPassword) throws PasswordMatchedOldException, PasswordIsWeakException;

    public int getDetails(String userId) throws UserNotFoundException;
}
