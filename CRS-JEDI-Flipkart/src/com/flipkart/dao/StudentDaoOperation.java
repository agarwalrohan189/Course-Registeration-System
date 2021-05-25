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
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.Grade;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentNotFoundException;
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
		List<Course> courses= new ArrayList<Course>();
		
		try {
			stmt = conn.prepareStatement(SQLQueries.GET_COURSE_CATALOGUE);
			ResultSet catalogue= stmt.executeQuery();
			
			
			while(catalogue.next()) {
				stmt = conn.prepareStatement(SQLQueries.GET_USER_NAME);
				stmt.setString(1,catalogue.getString("pid"));
				ResultSet profName=stmt.executeQuery();
				courses.add(new Course(catalogue.getInt("cid"), catalogue.getString("cname"), catalogue.getString("pid"), profName.getString("name"), catalogue.getInt("filledSeats")));
			}
		}
		catch(SQLException e) {
			throw new DatabaseException();
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
					stmt.close();
				}
				catch(SQLException ex){
					System.out.println(ex.getMessage());
					throw new DatabaseException();
				}
			}
		}
		return courses;
	}

	@Override
	public List<RegisteredCourse> viewGrades(String studentId) throws StudentNotFoundException, CourseNotFoundException {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		List<RegisteredCourse> courses= new ArrayList<RegisteredCourse>();
		
		try {
			stmt=conn.prepareStatement(SQLQueries.GET_REGISTERED_COURSE_DETAILS);
			stmt.setString(1, studentId);
			ResultSet details=stmt.executeQuery();
			
			while(details.next()) {
				
				//get grade from int value
				Grade grade=null;

				for (Grade g : Grade.values()) {
				    if (g.hasValue() == details.getInt("grade")) {
				        grade = g;
				        break;
				    }
				}
				
				//get profname-> resolve error
				Professor p=(Professor)UserDAOOperation.getDetails(details.getString("pid"));
				String profName=p.getName();
				
				//get coursename
				int cid=details.getInt("cid");
				ResultSet courseName=null;
				try {
					stmt=conn.prepareStatement(SQLQueries.GET_COURSE_NAME);
					stmt.setInt(1, cid);
					courseName=stmt.executeQuery();
				}
				catch(SQLException e) {
					throw new CourseNotFoundException(cid);
				}
				
				
				//semester no
				
		
				courses.add(new RegisteredCourse(courseName.getString("cname"), profName, studentId, details.getInt("semesterNum"), cid,
			grade));
			}
		}
		catch(SQLException e) {
			throw new StudentNotFoundException(studentId);
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
					stmt.close();
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
