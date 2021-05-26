/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;

import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseSeatsFullException;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.PaymentAlreadyDoneException;
import com.flipkart.exception.RegistrationNotCompleteException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;

/**
 * @author shubh
 *
 */
public class RegistrationOperation implements RegistrationInterface {

	RegistrationDaoInterface registrationDaoInterface = new RegistrationDaoOperation();
	UserInterface userInterface = UserOperation.getInstance();

	@Override
	public void registerCourses(String studentId) throws StudentNotFoundException{
		Check_if_possible();//>3
		registrationDaoInterface.registerCourses(studentId);
	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException, DatabaseException{
		Course course = registrationDaoInterface.getCourse(courseCode);
		if(course.getFilledSeats()>=Course.MAX_SEATS){
			throw new CourseSeatsFullException(course.getCourseId());
		}
		if(viewRegisteredCourses(studentId).size() >= Student.MAX_COURSES){
			throw new CourseLimitExceededException(Student.MAX_COURSES);
		}
		return registrationDaoInterface.addCourse(studentId, courseCode);
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException, DatabaseException{
		List<RegisteredCourse> regCourses = viewRegisteredCourses(studentId);
		boolean isRegistered=false;
		for(RegisteredCourse course : regCourses){
			if(course.getCourseId()==courseCode){
				isRegistered=true;
			}
		}
		if(!isRegistered){
			throw new CourseNotFoundException(courseCode);
		}
		return registrationDaoInterface.dropCourse(studentId, courseCode);
	}

	@Override
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException{
		return registrationDaoInterface.viewRegisteredCourses(studentId);
	}
	
	@Override
	public float calculateFee(String studentId) throws StudentNotFoundException{
		return registrationDaoInterface.calculateFee(studentId);
	}

	@Override
	public void payFee(String studentId, ModeOfPayment mode, float amount) throws StudentNotFoundException, RegistrationNotCompleteException, PaymentAlreadyDoneException{
		float feeToBePaid = calculateFee(studentId);
		if(!registrationDaoInterface.isRegistrationDone(studentId)) {
			throw new RegistrationNotCompleteException(studentId);
		}
		if(registrationDaoInterface.isPaymentDone(studentId)) {
			throw new PaymentAlreadyDoneException(studentId);
		}
		Payment payObj = new Payment(studentId, mode, amount);
		PaymentNotification notifObj = new PaymentNotification(payObj, feeToBePaid);
		if(payObj.isStatus()) {
			registrationDaoInterface.feePaid(studentId);
		}
		NotificationOperation NotifOp = new NotificationOperation();
		NotifOp.sendNotification(notifObj);
	}


}
