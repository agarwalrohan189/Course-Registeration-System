/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public interface StudentDaoInterface {
	
	/**
	 * @return Course Catalogue displaying all courses offered
	 */
	public List<Course> getCourseCatalogue() throws DatabaseException, SQLException;
	
	public List<RegisteredCourse> viewGrades(String StudentId) throws StudentNotFoundException, CourseNotFoundException;
	
	
}
