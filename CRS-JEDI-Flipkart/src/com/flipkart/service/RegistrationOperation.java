/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
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
