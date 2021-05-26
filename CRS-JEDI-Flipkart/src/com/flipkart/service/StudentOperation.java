/**
 * 
 */
package com.flipkart.service;

import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentAlreadyExistsException;
import com.flipkart.exception.StudentNotAddedException;
import com.flipkart.exception.StudentNotFoundException;

/**
 * @author rohanagarwal
 *
 */
public class StudentOperation implements StudentInterface {

	@Override
	public List<Course> viewCourseCatalogue() throws DatabaseException{
		// TODO Auto-generated method stub
		StudentDaoInterface studentDaoInterface= new StudentDaoOperation();
		return studentDaoInterface.getCourseCatalogue();
	}

	@Override
	public List<RegisteredCourse> viewGrades(String studentId) throws StudentNotFoundException {
		RegistrationInterface registrationInterface = new RegistrationOperation();
		return registrationInterface.viewRegisteredCourses(studentId);
	}

}
