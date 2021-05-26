/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * @author rohanagarwal
 *
 */
public class StudentDaoOperation implements StudentDaoInterface{
	
	private PreparedStatement stmt = null;

	@Override
	public List<Course> getCourseCatalogue() throws DatabaseException{
		Connection conn = DBUtil.getConnection();
		List<Course> courses= new ArrayList<Course>();
		
		try {
			stmt = conn.prepareStatement(SQLQueries.GET_COURSE_CATALOGUE);
			ResultSet catalogue= stmt.executeQuery();
			
			while(catalogue.next()) {
				courses.add(new Course(catalogue.getInt("cid"), catalogue.getString("cname"), catalogue.getString("pid"), UserDAOOperation.getInstance().getDetails(catalogue.getString("pid")).getName(), catalogue.getInt("filledSeats")));
			}
		}
		catch(SQLException e) {
			throw new DatabaseException();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				throw new DatabaseException();
			}
			finally {
				try {
					conn.close();
				}
				catch(SQLException ex){
					System.out.println(ex.getMessage());
					throw new DatabaseException();
				}
			}
		}
		return courses;
	}
}
