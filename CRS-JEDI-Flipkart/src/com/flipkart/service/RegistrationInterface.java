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
public interface RegistrationInterface {

	/**
	 * Register selected courses
	 * @param studentId
	 */
	public void registerCourses(String studentId);

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
			throws CourseNotFoundException, CourseLimitExceededException, CourseSeatsFullException;

	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public boolean dropCourse(String studentId, int courseCode) throws CourseNotFoundException;

	/**
	 * View registered courses
	 * @param studentId
	 * @return
	 */
	public List<Course> viewRegisteredCourses(String studentId);

	/**
	 * View grade card for the semester
	 * @param studentId
	 * @return
	 */
	public List<StudentGrade> viewGradeCard(String studentId);

	/**
	 * @param studentId
	 * @return
	 */
	public double calculateFee(String studentId);

// public List<Course> viewCourses(String studentId);
}
