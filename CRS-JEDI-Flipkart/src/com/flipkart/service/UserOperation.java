/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.constant.RoleConstant;
import com.flipkart.dao.UserDaoOperation;
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
public class UserOperation implements UserInterface {

	private static volatile UserOperation instance=null;

	private UserOperation()
	{	
	}
	
	/**
	 * Method to make UserOperation Singleton
	 * @return
	 */
	public static UserOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(UserOperation.class){
				instance=new UserOperation();
			}
		}
		return instance;
	}
	
	public RoleConstant login(String userID, String password) throws UserNotFoundException, PasswordMismatchException {
		return UserDaoOperation.getInstance().login(userID, password);
	}

	public boolean setPassword(String userID, String newPassword) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException{
		UserDaoOperation.getInstance().setPassword(userID, newPassword);
		return false;
	}

	public User getDetails(String userId) throws UserNotFoundException, ProfNotFoundException, StudentNotFoundException {
		return UserDaoOperation.getInstance().getDetails(userId);
	}

}
