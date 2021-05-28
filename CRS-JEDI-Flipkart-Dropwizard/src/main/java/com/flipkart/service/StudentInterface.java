/**
 * 
 */
package com.flipkart.service;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentAlreadyExistsException;
import com.flipkart.exception.StudentNotAddedException;
import com.flipkart.exception.StudentNotFoundException;

import java.util.List;

/**
 * below class describes operations for course registration and fee payment
 */
public interface StudentInterface {
	
	/**
     * @return list of courses available
     * @throws DatabaseException
     */
    public List<Course> viewCourseCatalogue() throws DatabaseException;

    /**
     * @param StudentId
     * @return list of courses the student has registered
     * @throws StudentNotFoundException
     */
    public List<RegisteredCourse> viewGrades(String StudentId) throws StudentNotFoundException;

    /**
     * @param student -> student class with all details of student
     * @throws StudentAlreadyExistsException
     * @throws StudentNotAddedException
     */
    public void signUp(Student student) throws StudentAlreadyExistsException, StudentNotAddedException;

    /**
     * @param studentID
     * @return true if student has been approved
     * @throws StudentNotFoundException
     */
    public boolean isApproved(String studentID) throws StudentNotFoundException;

} 
