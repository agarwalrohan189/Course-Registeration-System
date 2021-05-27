package com.flipkart.dao;


import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.*;

/**
 * RegistrationDaoInterface
 */
public interface RegistrationDaoInterface {

	/**
	 * Register selected courses
	 * @param studentId
	 */
	public void registerCourses(String studentId, HashMap<Integer,Boolean> courseIDs) throws StudentNotFoundException;

	/**
	 * Get details of the course from course ID
	 * @param courseId -> Course ID of course
	 * @return -> Course
	 * @throws CourseNotFoundException
	 */
	public Course getCourse(int courseId) throws CourseNotFoundException;

	/**
	 * Add a course
	 * @param studentId
	 * @param courseCode
	 * @return -> Whether course is added or not
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 * @throws CourseSeatsFullException
	 */
	public boolean addCourse(String studentId, int courseCode) throws DatabaseException;

	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return -> Whether course is dropped or not
	 * @throws CourseNotFoundException
	 */
	public boolean dropCourse(String studentId, int courseCode)  throws DatabaseException;

	/**
	 * View registered courses
	 * @param studentId
	 * @return List of registered courses
	 */
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException;

	/**
	 * Calculate fee of the student
	 * @param studentId -> Student ID of student whose fees are calculated
	 * @return Fee to be paid
	 */
	public float calculateFee(String studentId) throws StudentNotFoundException;

	/**
	 * Confirm the payment made
	 * @param studentId -> student ID of student whose confirmation is to be done
	 * @throws StudentNotFoundException
	 */
	public void feePaid(String studentId) throws StudentNotFoundException;

	/**
	 * Check whether registration is done or not
	 * @param studentId -> Student ID of student whose registration is being checked
	 * @return -> Whether registration is done or not
	 * @throws StudentNotFoundException
	 */
	public boolean isRegistrationDone(String studentId) throws StudentNotFoundException;

	/**
	 * Method to check whether payment is done or not
	 * @param studentId -> Student ID of student whose payment we're checking
	 * @return -> whether payment is done or not
	 * @throws StudentNotFoundException
	 */
	public boolean isPaymentDone(String studentId) throws StudentNotFoundException;

	/**
	 * Method to get notification
	 * @param notifId -> ID of notification
	 * @return -> Message of notification
	 * @throws NotifIdNotExistsException
	 */
	public String getNotification(int notifId) throws NotifIdNotExistsException;
}