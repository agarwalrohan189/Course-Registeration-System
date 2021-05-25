/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

/**
 * @author shubh
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface {

	@Override
	public void registerCourses(String studentId) throws SQLException{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException{
                Connection conn = DBUtils.getConnection();
		

                try 
                {
                    stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
                    stmt.setInt(1, studentId);
                    stmt.setString(2, courseCode);
        
                    stmt.executeUpdate();
                    
                    stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
                    stmt.setString(1, courseCode);
                    stmt.executeUpdate();
                    return true;
                }
                catch (SQLException e) 
                {
                    logger.info(e.getMessage());
                }
                finally
                {
                    stmt.close();
                    conn.close();
                }
                return false;
                
		return false;
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