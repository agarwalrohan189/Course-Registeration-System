/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.client.CRSApplication;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

/**
 * @author rohanagarwal
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	private static Logger logger = Logger.getLogger(StudentDaoOperation.class);

	private static volatile StudentDaoOperation instance = null;
	private PreparedStatement statement = null;

	private StudentDaoOperation(){}

	public static StudentDaoOperation getInstance() {
		if (instance == null) {
			synchronized (StudentDaoOperation.class) {
				instance = new StudentDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * Adds student to the database using SQL command
	 * @param student -> student to be added
	 * @throws UserAlreadyExistsException
	 */
	@Override
	public void signUp(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {

		try {
			AdminDaoOperation adminDaoInterface = AdminDaoOperation.getInstance();
			adminDaoInterface.addUser(student);

		}catch (UserNotAddedException e) {

			logger.error(e.getMessage());
			throw new StudentNotAddedException(student.getId());
		}

		statement = null;
		Connection conn = DBUtil.getConnection();
		try {

			String sql = SQLQueriesConstant.ADD_STUDENT;
			statement = conn.prepareStatement(sql);

			statement.setString(1, student.getId());
			statement.setString(2, student.getBranch());
			statement.setInt(3, student.getBatchYear());
			statement.setBoolean(4, student.isPaymentDone());
			int row = statement.executeUpdate();

			logger.info(row + " student added.");
			if(row == 0) {
				logger.info("Student with userId: " + student.getId() + " not added.");
				throw new StudentAlreadyExistsException(student.getId());
			}

			logger.info("User with userId: " + student.getUsername() + " added.");

		}catch(SQLException se) {

			logger.info(se.getMessage());
			throw new StudentNotAddedException(student.getId());

		}
		finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * Check whether student is approved or not
	 * @param studentID -> student ID of student to be checked
	 * @return -> Whether student is approved or not
	 * @throws StudentNotFoundException
	 */
	@Override
	public boolean isApproved(String studentID) throws StudentNotFoundException {
		Connection connection=DBUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstant.IS_APPROVED_STUDENT_QUERY);
			statement.setString(1, studentID);
			ResultSet rs = statement.executeQuery();

			if(rs.next())
			{
				return rs.getBoolean("isApproved");
			}

		}
		catch(SQLException e)
		{
			//slogger.error(e.getMessage());
			throw new  StudentNotFoundException(studentID);
		}
		finally {
			try {
				connection.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}

		return false;
	}

	/**
	 * Get course catalogue
	 * @return Course Catalogue displaying all courses offered
	 */
	@Override
	public List<Course> getCourseCatalogue() throws DatabaseException{
		Connection conn = DBUtil.getConnection();
		List<Course> courses= new ArrayList<Course>();
		
		try {
			statement = conn.prepareStatement(SQLQueriesConstant.GET_COURSE_CATALOGUE);
			ResultSet catalogue= statement.executeQuery();
			
			while(catalogue.next()) {
				courses.add(new Course(catalogue.getInt("cid"), catalogue.getString("cname"), catalogue.getString("pid"), UserDaoOperation.getInstance().getDetails(catalogue.getString("pid")).getName(), catalogue.getInt("filledSeats")));
			}
		}
		catch(SQLException e) {
			throw new DatabaseException();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		finally {
			try {
				statement.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				throw new DatabaseException();
			}
			finally {
				try {
					conn.close();
				}
				catch(SQLException ex){
					logger.info(ex.getMessage());
					throw new DatabaseException();
				}
			}
		}
		return courses;
	}
}
