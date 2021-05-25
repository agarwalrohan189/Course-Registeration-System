/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;

import java.util.List;

public interface UserInterface {
    public boolean login(String userID,String password) throws UserNotFoundException;

    boolean setPassword(String userID, String newPassword);

    public int getDetails(String userId);
}
