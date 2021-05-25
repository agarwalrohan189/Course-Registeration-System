/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseSeatsFullException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;

/**
 * @author shubh
 *
 */
public class RegistrationOperation implements RegistrationInterface {

	RegistrationDaoInterface registrationDaoInterface = new RegistrationDaoOperation();
	UserInterface userInterface = new UserOperation();

	@Override
	public void registerCourses(String studentId) throws StudentNotFoundException{

	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException{
		Course course = registrationDaoInterface.getCourse();
		if(course.getFilledSeats()>=Course.MAX_SEATS){
			throw new CourseSeatsFullException(course.getCourseId());
		}
		Student student = userInterface.getUser(studentId);
		if(student.getCoursesEnrolled().length>=MAX_COURSES){
			throw new CourseLimitExceededException(MAX_COURSES);
		}
		registrationDaoInterface.addCourse(studentId, courseCode);
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
