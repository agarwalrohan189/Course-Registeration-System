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

/**
 * @author shubh
 *
 */
public class RegistrationOperation implements RegistrationInterface {

	@Override
	public void registerCourses(String studentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentGrade> viewGradeCard(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateFee(String studentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRegistered(String studentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
