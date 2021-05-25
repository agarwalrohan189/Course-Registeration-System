/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseSeatsFullException;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author shubh
 *
 */
public class RegistrationOperation implements RegistrationInterface {

	@Override
	public void registerCourses(String studentId) throws StudentNotFoundException{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException{
		// TODO Auto-generated method stub
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
