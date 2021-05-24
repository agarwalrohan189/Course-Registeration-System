/**
 * 
 */
package com.flipkart.service;

/**
 * @author shubh
 *
 */
public interface StudentInterface {
    public int register(String name,String userID,String password,Gender gender,int batch,String branch,String address,String country) throws StudentNotRegisteredException;

    /**
     * Method to get Student ID from User ID
     * @param userId
     * @return Student ID
     */
    public int getStudentId(String userId);

    /**
     * Method to check if student is approved by Admin or not
     * @param studentId
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(int studentId);
}
