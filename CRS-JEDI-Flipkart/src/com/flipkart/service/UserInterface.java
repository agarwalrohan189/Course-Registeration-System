/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {
    public boolean login(String userID,String password) throws UserNotFoundException;

    boolean setPassword(String userID, String newPassword);

    public int getDetails(String userId) throws UserNotFoundException;
}
