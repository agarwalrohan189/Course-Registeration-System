/**
 * 
 */
package com.flipkart.dao;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.flipkart.constant.*;
import java.sql.SQLException;

/**
 * @author shubh
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface {
	
    private PreparedStatement statement = null;

	@Override
	public void registerCourses(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		try 
		{
			statement = conn.prepareStatement(SQLQueries.SET_REGISTRATION_STATUS);
			statement.setString(1, studentId);
			statement.executeUpdate();
		} 
		catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
	}

    @Override
    public Course getCourse(int courseId) throws CourseNotFoundException{
        Connection conn = DBUtil.getConnection();
        try
        {
            statement = conn.prepareStatement("Select * from CourseCatalogue where cid = ?");
            statement.setInt(1,courseId);
            ResultSet rs = statement.executeQuery();

            statement = conn.prepareStatement(SQLQueries.GET_USER_NAME);
            statement.setString(1,rs.getString("pid"));
            ResultSet profName=statement.executeQuery();

            Course course = new Course(rs.getInt("cid"), rs.getString("cname"), rs.getString("pid"), profName.getString("name"), rs.getInt("filledSeats"));
            return course;
        }
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new CourseNotFoundException(courseId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
	}

	@Override
	public boolean addCourse(String studentId, int courseCode) throws DatabaseException{
        Connection conn = DBUtil.getConnection();
        try
        {
            statement = conn.prepareStatement(SQLQueries.ADD_COURSE);
            statement.setString(1, studentId);
            statement.setInt(2, courseCode);

            statement.executeUpdate();
            
            statement = conn.prepareStatement(SQLQueries.DECREMENT_COURSE_SEATS);
            statement.setInt(1, courseCode);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new DatabaseException();
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws DatabaseException{
		Connection conn = DBUtil.getConnection();	
        try
        {
            statement = conn.prepareStatement(SQLQueries.DROP_COURSE);
            statement.setInt(1, courseCode);
            statement.setString(2, studentId);
            statement.execute();
            
            statement = conn.prepareStatement(SQLQueries.INCREMENT_COURSE_SEATS);
            statement.setInt(1, courseCode);
            statement.execute();
            
            statement.close();
            
            return true;
        }
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new DatabaseException();
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
	}

	@Override
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		List<RegisteredCourse> registeredCourseList = new ArrayList<>();
		try 
		{
			statement = conn.prepareStatement(SQLQueries.VIEW_REGISTERED_COURSES);
			statement.setString(1, studentId);

			ResultSet rs = statement.executeQuery();

            statement = conn.prepareStatement(SQLQueries.GET_USER_NAME);
            statement.setString(1,rs.getString("pid"));
            ResultSet profName=statement.executeQuery();
			
			while (rs.next()) {
                RegisteredCourse course = new RegisteredCourse(rs.getString("cname"), profName.getString("name"), studentId, rs.getInt("semesterNum"), rs.getInt("cid"), Grade.fromInt(rs.getInt("grade")));
				registeredCourseList.add(course);
			}
		}
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
		return registeredCourseList;
	}

	@Override
	public float calculateFee(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		int num_courses = 0;
		try 
		{
			statement = conn.prepareStatement(SQLQueries.GET_NUM_REGISTERED_COURSES);
			statement.setString(1, studentId);
			statement.setString(2, Integer.toString(SQLQueries.semesterYear));
			statement.setString(3, Integer.toString(SQLQueries.semesterNum));
			ResultSet rs = statement.executeQuery();
			num_courses = rs.getInt("count");
		} 
		catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
		return num_courses * SQLQueries.feesPerCourse;
		
	}
	
	@Override
	public void feePaid(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		try 
		{
			statement = conn.prepareStatement(SQLQueries.SET_PAYMENT_STATUS);
			statement.setString(1, studentId);
			statement.executeUpdate();
		} 
		catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
	}
	
	@Override
	public boolean isRegistrationDone(String studentId) throws StudentNotFoundException {
		Connection conn = DBUtil.getConnection();
		boolean regDone;
		try 
		{
			statement = conn.prepareStatement(SQLQueries.GET_REGISTRATION_STATUS);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			regDone = rs.getBoolean("isRegistered");
		} 
		catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
		return regDone;
	}

	@Override
	public boolean isPaymentDone(String studentId) throws StudentNotFoundException  {
		Connection conn = DBUtil.getConnection();
		boolean paid;
		try 
		{
			statement = conn.prepareStatement(SQLQueries.GET_PAYMENT_STATUS);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			paid = rs.getBoolean("paymentIsDone");
		} 
		catch (SQLException e) 
        {
            System.err.println(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                System.err.println("Couldn't close connection to database");
                System.err.println(e.getMessage());
            }
        }
		return paid;
	}

}