/**
 * 
 */
package com.flipkart.service;

import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.ModeOfPaymentConstant;
import com.flipkart.exception.*;

/**
 * @author shubh
 *
 */
public interface RegistrationInterface {

	/**
	 * Register selected courses
	 * @param studentId
	 * @param courseIDs
	 * @return true if courses have been registered successfully
	 * @throws StudentNotFoundException
	 */
	public boolean registerCourses(String studentId, HashMap<Integer,Boolean> courseIDs )throws StudentNotFoundException;
	
	/**
	 * Register selected courses
	 * @param studentId
	 * @return true if registration has been done
	 * @throws StudentNotFoundException
	 */
	public boolean isRegistrationDone(String studentId) throws StudentNotFoundException;

	/**
	 * Add a course
	 * @param studentId
	 * @param courseCode
	 * @return true if successfully added
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 * @throws CourseSeatsFullException
	 * @throws StudentNotFoundException
	 * @throws DatabaseException
	 */
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException, DatabaseException;

	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return true if course successfully dropped
	 * @throws CourseNotFoundException
	 * @throws StudentNotFoundException
	 * @throws DatabaseException
	 */
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException, DatabaseException;

	/**
	 * View registered courses
	 * @param studentId
	 * @return list of courses registered for the student
	 * @throws StudentNotFoundException
	 */
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException;

	/**
	 * calculate fee
	 * @param studentId
	 * @return fee
	 * @throws StudentNotFoundException
	 */
	public float calculateFee(String studentId) throws StudentNotFoundException;

	/**
	 * pay fee
	 * @param studentId -> id of student whose payment is to be done
	 * @param mode -> mode of payment
	 * @param amount -> amount to be paid
	 * @throws StudentNotFoundException
	 * @throws NotifIdNotExistsException
	 */
	public void payFee(String studentId, ModeOfPaymentConstant mode, float amount) throws StudentNotFoundException, NotifIdNotExistsException;
}
