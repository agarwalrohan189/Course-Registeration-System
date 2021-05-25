/**
 * 
 */
package com.flipkart.service;

public interface UserInterface {
    public boolean login(String userID,String password);

    boolean setPassword(String userID, String newPassword);

    public int getDetails(String userId);
}
