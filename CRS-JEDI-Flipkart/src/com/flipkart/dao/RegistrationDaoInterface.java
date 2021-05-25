package com.flipkart.dao;


import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;
import java.sql.SQLException;

/**
 * RegistrationDaoInterface
 */
public interface RegistrationDaoInterface {

	/**
	 * Register selected courses
	 * @param studentId
	 */
	public void registerCourses(String studentId) throws SQLException;

	/**
	 * Add a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceededException
	 * @throws CourseSeatsFullException
	 */
	public boolean addCourse(String studentId, int courseCode) throws CourseNotException;
	/**
	 * Drop a course
	 * @param studentId
	 * @param courseCode
	 * @return
	 * @throws CourseNotFoundException
	 */
	public boolean dropCourse(String studentId, int courseCode) throws SQLException;

	/**
	 * View registered courses
	 * @param studentId
	 * @return
	 */
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException;

	/**
	 * View grade card for the semester
	 * @param studentId
	 * @return
	 */
	public List<StudentGrade> viewGradeCard(String studentId) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 */
	public double calculateFee(String studentId) throws SQLException;
}