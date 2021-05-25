/**
 * 
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.exception.*;

/**
 * @author shubh
 *
 */
public interface RegistrationInterface {

	/**
	 * Register selected courses
	 * @param studentId
	 */
	public void registerCourses(String studentId)throws StudentNotFoundException;

	/**
	 * Add a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 * @throws CourseSeatsFullException
	 */
	public boolean addCourse(String studentId, int courseCode)
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException, StudentNotFoundException, DatabaseException;

	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException, StudentNotFoundException, DatabaseException;

	/**
	 * View registered courses
	 * @param studentId
	 * @return
	 */
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException;

	/**
	 * @param studentId
	 * @return
	 */

	public float calculateFee(String studentId) throws StudentNotFoundException;

	public void payFee(String studentId, ModeOfPayment mode, float amount) throws StudentNotFoundException;
}
