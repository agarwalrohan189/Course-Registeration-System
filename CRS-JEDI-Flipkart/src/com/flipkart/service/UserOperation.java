/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDAOOperation;
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
	
	public boolean login(String userID, String password) throws UserNotFoundException, PasswordMismatchException {
		// TODO Auto-generated method stub
		UserDAOOperation.getInstance().login(userID, password);
		return false;
	}

	public boolean setPassword(String userID, String newPassword) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException{
		// TODO Auto-generated method stub
		UserDAOOperation.getInstance().setPassword(userID, newPassword);
		return false;
	}

	public User getDetails(String userId) throws UserNotFoundException, ProfNotFoundException, StudentNotFoundException {
		// TODO Auto-generated method stub	
		return UserDAOOperation.getInstance().getDetails(userId);
	}

}
