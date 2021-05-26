/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.exception.DatabaseException;

/**
 * @author rohanagarwal
 *
 */
public interface StudentDaoInterface {
	
	/**
	 * @return Course Catalogue displaying all courses offered
	 */
	public List<Course> getCourseCatalogue() throws DatabaseException, SQLException;
}
