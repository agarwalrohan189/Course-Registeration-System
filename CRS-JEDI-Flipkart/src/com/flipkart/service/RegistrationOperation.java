/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;

import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;

import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
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
		registrationDaoInterface.registerCourses(studentId);
	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException{
		Course course = registrationDaoInterface.getCourse(courseCode);
		if(course.getFilledSeats()>=Course.MAX_SEATS){
			throw new CourseSeatsFullException(course.getCourseId());
		}
		Student student = userInterface.getUser(studentId);
		if(student.getCoursesEnrolled().length>=MAX_COURSES){
			throw new CourseLimitExceededException(MAX_COURSES);
		}
		return registrationDaoInterface.addCourse(studentId, courseCode);
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException{
		registrationDaoInterface.getCourse();
		userInterface.getUser(studentId);
		return registrationDaoInterface.dropCourse(studentId, courseCode);
	}

	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws StudentNotFoundException{
		return ((Student)userInterface.getUser(studentId)).getCoursesEnrolled();
	}

	@Override
	public float calculateFee(String studentId){
		// TODO Auto-generated method stub
		return 100;
	}
	
	@Override
	public void payFee(String studentId, ModeOfPayment mode, float amount) {
		
		float feeToBePaid = calculateFee(studentId);
		Payment payObj = new Payment(studentId, mode, amount);
		PaymentNotification notifObj = new PaymentNotification(payObj, feeToBePaid);
		if(payObj.isStatus()) {
			//decrement fee
		}
		NotificationOperation NotifOp = new NotificationOperation();
		NotifOp.sendNotification(notifObj);
	}


}
