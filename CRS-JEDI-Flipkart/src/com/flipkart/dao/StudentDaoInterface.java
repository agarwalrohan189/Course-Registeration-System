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
	 * @return Course Catalogue displaying all courses offered
	 */
	public List<Course> getCourseCatalogue() throws DatabaseException;

	public void signUp(Student student) throws StudentAlreadyExistsException, StudentNotAddedException;

	public boolean isApproved(String studentID) throws StudentNotFoundException;
}
