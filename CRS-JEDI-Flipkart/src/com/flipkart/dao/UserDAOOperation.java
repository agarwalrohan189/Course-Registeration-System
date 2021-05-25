/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.constant.Gender;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.PasswordIsWeakException;
import com.flipkart.exception.PasswordMatchedOldException;
import com.flipkart.exception.PasswordMismatchException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * @author Shubham
 *
 */
public class UserDAOOperation implements UserDAOInterface{

	private static volatile UserDAOOperation instance = null;
	
	private UserDAOOperation()
	{		
	}
	
	public static UserDAOOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(UserDAOOperation.class){
				instance=new UserDAOOperation();
			}
		}
		return instance;
	}
	
	public boolean login(String userID, String password) throws PasswordMismatchException {
		Connection conn = DBUtil.getConnection();
		try
		{
			User user = getDetails(userID);
			if (password != user.getPassword())
				throw new PasswordMismatchException(userID);
			else
				return true;
		}
		catch(PasswordMismatchException e)
		{
			System.out.println(e.getMessage());
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setPassword(String userId, String newPassword)
			throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		try {
			
			User user = getDetails(userId);
			if (user.getPassword() == newPassword)
				throw new PasswordMatchedOldException (userId);
			
			if (newPassword.length() < 8)
				throw new PasswordIsWeakException(newPassword);
			
			PreparedStatement updatePassword = conn.prepareStatement(SQLQueries.UPDATE_PASSWORD);
			updatePassword.setString(1, newPassword);
			updatePassword.setString(2, userId);
			updatePassword.executeQuery();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public User getDetails(String userId) throws UserNotFoundException, StudentNotFoundException, ProfNotFoundException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		try
		{
			PreparedStatement getUserDetails = conn.prepareStatement(SQLQueries.GET_USER_DETAILS_QUERY);
			getUserDetails.setString(1, userId);
			ResultSet userResult = getUserDetails.executeQuery();
			
			if (!userResult.next())
				throw new UserNotFoundException(userId);
			else
			{
				String password = userResult.getString("password");
				String name = userResult.getString("name");
				Gender gender = Gender.getName(userResult.getInt("gender"));
				Role role = Role.values()[userResult.getInt("role")];
				String address = userResult.getString("address");
				String username = userResult.getString("username");
				Date dob = userResult.getDate("dob");
				if (role == Role.Student)
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_STUDENT_DETAILS_QUERY);
						stmt.setString(1, userId);
						ResultSet studentResult = stmt.executeQuery();
						
						if (!studentResult.next())
							throw new StudentNotFoundException(userId);
						else
						{
							String branch = studentResult.getString("branch");
							int batchYear = studentResult.getInt("batchYear");
							boolean paymentDone = studentResult.getBoolean("paymentIsDone");
							return new Student(userId, name, role, password, gender, address, username, dob, branch, batchYear, paymentDone);
						}
					}
					catch(SQLException e)
					{
						System.out.println(e.getMessage());
					}
				}
				else if (role == Role.Professor)
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_PROFESSOR_DETAILS_QUERY);
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
						System.out.println(e.getMessage());
					}
				}
				else
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement(SQLQueries.GET_ADMIN_DETAILS_QUERY);
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
						System.out.println(e.getMessage());
					}
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
