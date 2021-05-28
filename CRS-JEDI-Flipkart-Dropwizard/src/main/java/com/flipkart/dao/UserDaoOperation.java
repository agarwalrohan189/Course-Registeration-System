/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;
import com.flipkart.constant.*;

/**
 * @author Shubham
 *
 */
public class UserDaoOperation implements UserDaoInterface{
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);

	private static volatile UserDaoOperation instance = null;
	
	private UserDaoOperation()
	{		
	}
	
	public static UserDaoOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(UserDaoOperation.class){
				instance=new UserDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * Method for logging in
	 * @param userID -> userID of user logging in
	 * @param password -> Password of user logging in
	 * @return -> Role of the user
	 * @throws PasswordMismatchException
	 */
	public RoleConstant login(String userID, String password) throws PasswordMismatchException {
		try
		{
			User user = getDetails(userID);
			if (!password.equals (user.getPassword()))
				throw new PasswordMismatchException(userID);
			else
				return user.getRole();
		}
		catch(PasswordMismatchException e)
		{
			logger.info(e.getMessage());
		} catch (Exception e){
			logger.info(e.getMessage());
		}
		return null;
	}

	/**
	 * Set password for the user
	 * @param userId -> userID of user logging in
	 * @param newPassword -> Password of user logging in
	 * @return -> whether password is set or not
	 * @throws UserNotFoundException
	 * @throws PasswordMatchedOldException
	 * @throws PasswordIsWeakException
	 */
	public boolean setPassword(String userId, String newPassword)
			throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException {
		Connection conn = DBUtil.getConnection();
		try {
			
			User user = getDetails(userId);
			if (user.getPassword().equals (newPassword))
				throw new PasswordMatchedOldException (userId);
			
			if (newPassword.length() < 8)
				throw new PasswordIsWeakException(newPassword);
			
			PreparedStatement updatePassword = conn.prepareStatement(SQLQueriesConstant.UPDATE_PASSWORD);
			updatePassword.setString(1, newPassword);
			updatePassword.setString(2, userId);
			updatePassword.executeQuery();
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		} catch (StudentNotFoundException e) {
			logger.info(e.getMessage());
		} catch (ProfNotFoundException e) {
			logger.info(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * Get details of the user
	 * @param userId -> userID of user whose details we want
	 * @return -> User object containing details of user
	 * @throws UserNotFoundException
	 * @throws StudentNotFoundException
	 * @throws ProfNotFoundException
	 */
	public User getDetails(String userId) throws UserNotFoundException, StudentNotFoundException, ProfNotFoundException {
		Connection conn = DBUtil.getConnection();
		try
		{
			PreparedStatement getUserDetails = conn.prepareStatement(SQLQueriesConstant.GET_USER_DETAILS_QUERY);
			getUserDetails.setString(1, userId);
			ResultSet userResult = getUserDetails.executeQuery();
			
			if (!userResult.next())
				throw new UserNotFoundException(userId);
			else
			{
				String password = userResult.getString("password");
				String name = userResult.getString("name");
				GenderConstant gender = GenderConstant.getName(userResult.getInt("gender"));
				RoleConstant role = RoleConstant.values()[userResult.getInt("role")];
				String address = userResult.getString("address");
				String username = userResult.getString("username");
				Date dob = userResult.getDate("dob");
				if (role == RoleConstant.Student)
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.GET_STUDENT_DETAILS_QUERY);
						stmt.setString(1, userId);
						ResultSet studentResult = stmt.executeQuery();
						
						if (!studentResult.next())
							throw new StudentNotFoundException(userId);
						else
						{
							String branch = studentResult.getString("branch");
							int batchYear = studentResult.getInt("batchYear");
							boolean paymentDone = studentResult.getBoolean("paymentIsDone");
							boolean isRegistered = studentResult.getBoolean("isRegistered");
							return new Student(userId, name, role, password, gender, address, username, dob, branch, batchYear, paymentDone, isRegistered);
						}
					}
					catch(SQLException e)
					{
						logger.info(e.getMessage());
					}
				}
				else if (role == RoleConstant.Professor)
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.GET_PROFESSOR_DETAILS_QUERY);
						stmt.setString(1, userId);
						ResultSet profresult = stmt.executeQuery();
						
						if (!profresult.next())
							throw new ProfNotFoundException(userId);
						else
						{
							String dept = profresult.getString("department");
							String qualification = profresult.getString("qualification");
							Date doj = profresult.getDate("doj");
							return new Professor(userId, username, role, password, gender, address, username, dob, dept, qualification, doj);
						}
					}
					catch(SQLException e)
					{
						logger.info(e.getMessage());
					}
				}
				else
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.GET_ADMIN_DETAILS_QUERY);
						stmt.setString(1, userId);
						ResultSet adminresult = stmt.executeQuery();
						
						if (!adminresult.next())
							throw new UserNotFoundException(userId);
						else
						{
							Date doj = adminresult.getDate("doj");
							return new Admin(userId, username, role, password, gender, address, username, dob, doj);
						}
					}
					catch(SQLException e)
					{
						logger.info(e.getMessage());
					}
				}
			}
		}
		catch(SQLException e)
		{
			logger.info(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return null;
	}

}
