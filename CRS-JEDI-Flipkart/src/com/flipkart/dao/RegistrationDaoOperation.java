/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import java.sql.Connection;
import java.sql.DriverManager;
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
		// TODO Auto-generated method stub

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
            statement.setString(1,catalogue.getString("pid"));
            ResultSet profName=stmt.executeQuery();

            Course course = new Course(rs.getInt("cid"), rs.getString("cname"), rs.getInt("pid"), instructorName, rs.getInt("filledSeats"));
            return course;
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            statement.close();
            conn.close();
        }
	}

	@Override
	public boolean addCourse(String studentId, int courseCode) throws DatabaseException{
        Connection conn = DBUtil.getConnection();
        try
        {
            statement = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            statement.setInt(1, studentId);
            statement.setString(2, courseCode);

            statement.executeUpdate();
            
            statement = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
            statement.setString(1, courseCode);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) 
        {
            logger.info(e.getMessage());
        }
        finally
        {
            statement.close();
            conn.close();
        }
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws StudentNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentGrade> viewGradeCard(String studentId) throws StudentNotFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateFee(String studentId) throws StudentNotFoundException{
		// TODO Auto-generated method stub
		return 0;
	}

}