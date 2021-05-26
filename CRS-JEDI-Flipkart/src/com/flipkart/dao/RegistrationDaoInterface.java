package com.flipkart.dao;


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
	public void registerCourses(String studentId) throws StudentNotFoundException;

	public Course getCourse(int courseId) throws CourseNotFoundException;

	/**
	 * Add a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 * @throws CourseSeatsFullException
	 */
	public boolean addCourse(String studentId, int courseCode) throws DatabaseException;
	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public boolean dropCourse(String studentId, int courseCode)  throws DatabaseException;

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

	public void feePaid(String studentId) throws StudentNotFoundException;
}