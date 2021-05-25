/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DatabaseException;
import com.flipkart.utils.DBUtil;

/**
 * @author rohanagarwal
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	
	private PreparedStatement stmt = null;

	@Override
	public List<Course> getCourseCatalogue() throws DatabaseException{
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		
		try {
			stmt = conn.prepareStatement(SQLQueries.GET_COURSE_CATALOGUE);
			ResultSet catalogue= stmt.executeQuery();
			List<Course> courses;
			stmt = conn.prepareStatement(SQLQueries.);
			while(catalogue.next()) {
				courses.add(new Course(catalogue.getString("cid"), catalogue.getString("cname"), catalogue.getString("pid"), String instructorName, int filledSeats))
			}
		}
		catch(SQLException e) {
			throw new DatabaseException();
		}
		finally {
			stmt.close();
			conn.close();
		}
		return 
	}

}
