/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentAlreadyExistsException;
import com.flipkart.exception.StudentNotAddedException;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public interface StudentDaoInterface {
	
	/**
	 * Get course catalogue
	 * @return Course Catalogue displaying all courses offered
	 */
	public List<Course> getCourseCatalogue() throws DatabaseException;

	/**
	 * Method to sign up
	 * @param student -> student signing up
	 * @throws StudentAlreadyExistsException
	 * @throws StudentNotAddedException
	 */
	public void signUp(Student student) throws StudentAlreadyExistsException, StudentNotAddedException;

	/**
	 * Check whether student is approved or not
	 * @param studentID -> student ID of student to be checked
	 * @return -> Whether student is approved or not
	 * @throws StudentNotFoundException
	 */
	public boolean isApproved(String studentID) throws StudentNotFoundException;
}
