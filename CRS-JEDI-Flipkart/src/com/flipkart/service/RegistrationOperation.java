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
import com.flipkart.exception.NotifIdNotExistsException;
import com.flipkart.exception.PaymentAlreadyDoneException;
import com.flipkart.exception.RegistrationNotCompleteException;
import com.flipkart.exception.StudentNotFoundException;
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
	public boolean registerCourses(String studentId) throws StudentNotFoundException{ //status=alternate, primary or registered
		if(registrationDaoInterface.isRegistrationDone(studentId)) {
			return false;
		}
		registrationDaoInterface.registerCourses(studentId);
		return true;
	}
	
	@Override
	public boolean isRegistrationDone(String studentId) throws StudentNotFoundException{
		return registrationDaoInterface.isRegistrationDone(studentId);
	}

	@Override
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException, DatabaseException{
		if(!registrationDaoInterface.isRegistrationDone(studentId)) {
			return false;
		}
		Course course = registrationDaoInterface.getCourse(courseCode);
		if(course.getFilledSeats()>=Course.MAX_SEATS){
			throw new CourseSeatsFullException(course.getCourseId());
		}
		if(viewRegisteredCourses(studentId).size() >= Student.MAX_COURSES){
			throw new CourseLimitExceededException(Student.MAX_COURSES);
		}
		registrationDaoInterface.addCourse(studentId, courseCode);
		return true;
	}

	@Override
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException, DatabaseException{
		if(!registrationDaoInterface.isRegistrationDone(studentId)) {
			return false;
		}
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
		registrationDaoInterface.dropCourse(studentId, courseCode);
		return true;
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
	public void payFee(String studentId, ModeOfPayment mode, float amount) throws StudentNotFoundException, NotifIdNotExistsException{
		float feeToBePaid = calculateFee(studentId);
		String message;
//		PaymentNotification notifObj = new PaymentNotification();
		if(!registrationDaoInterface.isRegistrationDone(studentId)) {
			message = "Registration not yet complete";
//			throw new RegistrationNotCompleteException(studentId);
		}
		else if(registrationDaoInterface.isPaymentDone(studentId)) {
			message = "Payment already done";
//			throw new PaymentAlreadyDoneException(studentId);
		}else if(amount != feeToBePaid) {
			// should not happen in current code
			message =  "Amount chosen to pay and fee to be paid are different";
		}else {
			Payment payObj = new Payment(studentId, mode, amount);
			if(payObj.isStatus()) {
				registrationDaoInterface.feePaid(studentId);
				message = "Payment Successful! Thank You";
			}else {
				message = "Payment Failed! Try again later";
			}
			
		}
		NotificationOperation.getInstance().sendNotification(studentId, "payment", message);
//		NotificationOperation NotifOp = new NotificationOperation();
		//Notification to be sent here
//		NotifOp.sendNotification(notifObj);
	}


}
